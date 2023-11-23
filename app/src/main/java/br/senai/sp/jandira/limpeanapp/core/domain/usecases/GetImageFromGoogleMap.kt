package br.senai.sp.jandira.limpeanapp.core.domain.usecases

import androidx.compose.ui.graphics.vector.ImageVector
import br.senai.sp.jandira.limpeanapp.core.data.remote.GoogleApi
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.HttpException
import retrofit2.http.Url
import java.io.IOException
import javax.inject.Inject

private val KEY = "AIzaSyCLXaX9MfiGsaZOonUpXtsfC6CN8AaRZcE"
class GetImageFromGoogleMap @Inject constructor(
    private val googleApi : GoogleApi,
) {

    operator fun invoke(address: Address) : Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())

            val googleMapsResults = googleApi.getMapInfoFromCep(
                cep = address.cep.toInt(),
                key = KEY
            )
            val location = googleMapsResults.results.first().geometry.location

            val size = "200x200"
            val imageUrl = "https://maps.googleapis.com/maps/api/streetview?size=${size}&location=${location}&key=${KEY}"
            val local = LatLng(location.lat, location.lng)

            emit(Resource.Success(imageUrl))

        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Algum erro de internet inesperado ocorreu."))
        }
        catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?: "Algum erro de operação inesperado ocorreu."))
        }


    }
}