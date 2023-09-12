package br.senai.sp.jandira.limpeanapp.data.api

import br.senai.sp.jandira.limpeanapp.registration.RegistrationState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/cadastro")
    suspend fun registerClient(@Body data: RegistrationState): Response<BaseResponse>
}