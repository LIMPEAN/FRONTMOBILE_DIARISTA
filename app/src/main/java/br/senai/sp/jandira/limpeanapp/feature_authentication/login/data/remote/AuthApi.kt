package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.remote

import br.senai.sp.jandira.limpeanapp.core.data.remote.BaseResponse
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(
        @Body request: AuthRequest
    ) : TokenResponse


    @POST("cadastro")
    suspend fun register(
        @Body diarist : RegisterRequest
    ): Response<BaseResponse>
}