package br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {

    @GET("{cep}/json/")
    suspend fun getAddressByCep(
        @Path("cep") id:String
    ): ViaCepResponse
}