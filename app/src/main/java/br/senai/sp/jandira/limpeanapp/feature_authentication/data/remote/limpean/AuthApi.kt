package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.dto.AuthRequest
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristDto
import br.senai.sp.jandira.limpeanapp.di.AuthInterceptor
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(
        @Body request: AuthRequest
    ) : TokenResponse


    @POST("cadastro")
    suspend fun register(
        @Body diarist : RegisterRequest
    ): Response<BaseResponseDto>

    //
    @GET("verify/jwt")
    suspend fun authenticate()

}