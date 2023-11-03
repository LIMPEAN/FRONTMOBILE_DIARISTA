package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep

import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {

    @GET("{cep}/json/")
    suspend fun getAddressByCep(
        @Path("cep") id:String
    ): ViaCepResponse
}