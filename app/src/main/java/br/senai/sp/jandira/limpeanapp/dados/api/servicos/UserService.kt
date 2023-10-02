package br.senai.sp.jandira.limpeanapp.dados.api.servicos


import br.senai.sp.jandira.limpeanapp.dados.api.ApiResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("cadastro")
    fun createUser(@Body user: UserApi): Call<ApiResponse>
}