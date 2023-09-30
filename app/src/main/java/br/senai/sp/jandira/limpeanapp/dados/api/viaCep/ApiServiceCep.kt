package br.senai.sp.jandira.limpeanapp.dados.api.viaCep

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceCep {

    @GET("{cep}/json/")
    suspend fun getAddressByCep(@Path("cep") id:String): Response<AddressResponse>

}