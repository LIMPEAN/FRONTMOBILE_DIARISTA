package br.senai.sp.jandira.limpeanapp.dados.viaCep

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceCep {

    @GET("{cep}/json/")
    suspend fun getAdressByCep(@Path("cep") id:String): Response<BaseResponseCep>

}