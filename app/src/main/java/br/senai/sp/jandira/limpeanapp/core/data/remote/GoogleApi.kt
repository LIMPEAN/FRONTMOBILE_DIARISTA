package br.senai.sp.jandira.limpeanapp.core.data.remote

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps.GoogleMapsResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {

    @GET("https://maps.googleapis.com/maps/api/geocode/json")
    suspend fun getMapInfoFromCep(
        @Query("address") cep : Number,
        @Query("key") key : String
    ) : GoogleMapsResultsDto
}
