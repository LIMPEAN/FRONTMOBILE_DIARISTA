package br.senai.sp.jandira.limpeanapp.core.data.remote

import android.util.Size
import androidx.compose.ui.graphics.vector.ImageVector
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps.GoogleMapsResultsDto
import com.google.android.gms.maps.model.LatLng
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {

    @GET("https://maps.googleapis.com/maps/api/geocode/json")
    suspend fun getMapInfoFromCep(
        @Query("address") cep : Number,
        @Query("key") key : String
    ) : GoogleMapsResultsDto

    @GET("https://maps.googleapis.com/maps/api/streetview")
    suspend fun getImageUriFromLocal(
        @Query("location") local : LatLng,
        @Query("size") size : Size,
        @Query("key") key : String
    ) : ImageVector
}
