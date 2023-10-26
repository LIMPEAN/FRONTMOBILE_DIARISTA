package br.senai.sp.jandira.limpeanapp.home.data.remote

import retrofit2.http.GET

interface DiaristApi {

    @GET("diarist")
    suspend fun getDiarist() : GetDiaristResponse

    @GET("diarists")
    suspend fun getListOfDiarists() : ListDiaristsResponse
}