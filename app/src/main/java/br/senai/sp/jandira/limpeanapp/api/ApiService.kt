package br.senai.sp.jandira.limpeanapp.api

import br.senai.sp.jandira.limpeanapp.registration.RegistrationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/cadastro")
    suspend fun registerClient(@Body data: RegistrationData): Response<BaseResponse>
}