package br.senai.sp.jandira.limpeanapp.dados.api

import com.google.gson.JsonObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("cadastro")
    fun cadastrarUsuario(@Body resquestBody : RequestBody) : Call<ResponseBody>
}

class FakeApiService : ApiService { // Assuming YourApiService is an interface
    override  fun cadastrarUsuario(@Body resquestBody: RequestBody): Call<ResponseBody> {
        TODO("Not yet implemented")
    }
}