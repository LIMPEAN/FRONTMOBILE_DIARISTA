package br.senai.sp.jandira.limpeanapp.dados.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("/cadastro")
    fun cadastrarUsuario(requestBody : RequestBody) : Call<ResponseBody>
}