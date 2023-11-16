package br.senai.sp.jandira.limpeanapp.core.data.remote

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristsDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface DiaristApi {

    @GET("diarist")
    suspend fun getDiarist() : GetDiaristDto

    @GET("diarists")
    suspend fun getListOfDiarists() : GetDiaristsDto

    @PUT("diarist")
    suspend fun updateDiarist(diaristDto: DiaristDto)
    

    @GET("diarist/service")
    suspend fun getInvites()

    @GET("diarist/service")
    suspend fun getInviteById(@Query("id") id : Number)


    @PUT("diarist/service/price")
    suspend fun updatePrice()

}
