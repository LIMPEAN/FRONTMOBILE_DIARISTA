package br.senai.sp.jandira.limpeanapp.core.domain.usecases

import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.GoogleApi
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

data class GoogleMapState(
    val isLoading : Boolean = false,
    val local: LatLng? = null,
    val name: String = "",
    val place: String = "",
)
private val KEY = "AIzaSyCLXaX9MfiGsaZOonUpXtsfC6CN8AaRZcE"

class GetPropertiesForGoogleMapUseCase @Inject constructor(
    private val googleApi : GoogleApi,
) {

    operator fun invoke(cleaning : Cleaning) : Flow<Resource<GoogleMapState>> = flow {
        try {
            emit(Resource.Loading())

            val address = cleaning.address
            val googleMapsResults = googleApi.getMapInfoFromCep(
                cep = address.cep.toInt(),
                key = KEY
            )
            val location = googleMapsResults.results.first().geometry.location
            val local = LatLng(location.lat, location.lng)
            val googleMapState = GoogleMapState(
                local = local,
                name = "Agendado por ${cleaning.client}",
                place = "${address.state}, ${address.number}"
            )
            emit(Resource.Success(googleMapState))

        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Algum erro de internet inesperado ocorreu."))
        }
        catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?: "Algum erro de operação inesperado ocorreu."))
        }


    }
}