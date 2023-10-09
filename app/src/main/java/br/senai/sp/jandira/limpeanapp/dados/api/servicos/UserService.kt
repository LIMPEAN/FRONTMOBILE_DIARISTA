package br.senai.sp.jandira.limpeanapp.dados.api.servicos


import br.senai.sp.jandira.limpeanapp.authentication.login.LoginApi
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginResponse
import br.senai.sp.jandira.limpeanapp.dados.api.ApiResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @POST("cadastro")
    fun createUser(@Body user: UserApi): Call<ApiResponse>


    @POST("login")
    fun login(@Body login : LoginApi) : Call<LoginResponse>
}