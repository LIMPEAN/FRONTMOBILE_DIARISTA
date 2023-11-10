package br.senai.sp.jandira.limpeanapp.core.data.remote

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristsDto
import br.senai.sp.jandira.limpeanapp.di.AuthInterceptor
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface DiaristApi {

    @GET("diarist")
    suspend fun getDiarist() : GetDiaristDto

    @GET("diarists")
    suspend fun getListOfDiarists() : GetDiaristsDto

    @DELETE("diarist")
    suspend fun deleteDiarist()


}