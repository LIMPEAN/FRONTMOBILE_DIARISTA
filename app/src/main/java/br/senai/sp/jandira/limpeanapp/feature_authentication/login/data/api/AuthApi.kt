package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(
        @Body request: AuthRequest
    ) : TokenResponse
}