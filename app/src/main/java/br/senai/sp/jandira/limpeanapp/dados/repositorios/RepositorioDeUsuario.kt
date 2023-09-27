package br.senai.sp.jandira.limpeanapp.dados.repositorios

import android.util.Log
import br.senai.sp.jandira.limpeanapp.dados.api.ApiService
import br.senai.sp.jandira.limpeanapp.dados.api.BaseResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioDeUsuario(
    private val apiService : ApiService
) {

    fun adicionarUsuario(usuario: DiaristaApi){
       val body = Gson().toJson(usuario)
//        .toRequestBody("application/json; charset=UTF-8".toMediaType())

        Log.i("BODY-REPOSITORIO", body.toString())
//        apiService.cadastrarUsuario(body).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(
//                call: Call<ResponseBody>,
//                response: Response<ResponseBody>
//            ) {
//                if(response.isSuccessful){
//                    val message = response.body()?.string()
//                    val teste =  Gson().fromJson(message, BaseResponse::class.java)
//
//                    Log.i("teste", teste.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//               Log.i("Failure", t.toString())
//            }
//
//        })
    }

}