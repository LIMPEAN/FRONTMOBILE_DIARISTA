package br.senai.sp.jandira.limpeanapp.dados.repositorios

import android.util.Log
import br.senai.sp.jandira.limpeanapp.dados.api.ApiService
import br.senai.sp.jandira.limpeanapp.dados.api.BaseResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioDeDiarista(
    private val apiService : ApiService
) {

     fun adicionarDiarista(diarista: DiaristaApi) : Boolean{
        val corpo = Gson().toJson(diarista).toRequestBody()
         val requestBody = Request.Builder()
             //Construir requisição
        var statusDaRequisicao = false
        apiService.cadastrarUsuario(corpo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    Log.i("REQUISICAO-OK", response.toString())
                    val message = response.body()?.string()
//                    val res = Gson().fromJson(message, BaseResponse::class.java)
                    statusDaRequisicao = true
                    Log.i ("REQUISICAO-OK-RES", message.toString())
                } else {
                    Log.i("REQUISICAO-RUIM", response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("REQUISICAO-FAILURE", t.toString())
            }

        })
        return statusDaRequisicao
    }

}