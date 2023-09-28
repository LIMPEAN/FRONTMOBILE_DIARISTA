package br.senai.sp.jandira.limpeanapp.dados.api


import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET


import retrofit2.http.Headers

import retrofit2.http.POST


interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("cadastro")

    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>

    @GET("diarist")
    suspend fun listAllDiarists() : Response<DiaristaResponse>
//
//    @Headers("Content-Type: application/json")
//    @POST("cadastro")
//    fun create(@Body userData : UserApi) : Call<BaseResponse>
//
////
//}
//
//class FakeApiService : ApiService { // Assuming YourApiService is an interface
//    override fun cadastrarUsuario(usuario: DiaristaApi): Call<ResponseBody> {
//        TODO("Not yet implemented")
//    }

}