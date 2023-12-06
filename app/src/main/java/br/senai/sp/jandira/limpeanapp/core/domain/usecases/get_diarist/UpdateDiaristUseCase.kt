package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.update_diarist.Address
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.update_diarist.Phone
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.update_diarist.UpdateDiaristDto
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateDiaristUseCase @Inject constructor(
    private val diaristApi: DiaristApi
) {

    operator fun invoke(diarist : Diarist) : Flow<Resource<BaseResponseDto>> = flow {
        try {
            emit(Resource.Loading())
            var updatedAddressDto: Address
            diarist.address.first().apply {
                updatedAddressDto = Address(
                    cep = cep,
                    city = city,
                    complement = complement,
                    state = state,
                    district = district,
                    houseNumber = number,
                    publicPlace = publicPlace
                )
            }
            var updatedPhoneDto = emptyList<Phone>()

            diarist.phones[0].apply {
                updatedPhoneDto = listOf(
                    Phone()
                )
            }

            var updatedDiaristDto: UpdateDiaristDto
            diarist.apply {
                updatedDiaristDto = UpdateDiaristDto(
                    name = name,
                    biography = biography,
                    idGender = gender.id,
                    averagePrice = null,
                    photoUser = photo,
                    address = Address(),
                    phones = updatedPhoneDto
                )
            }


            val response = diaristApi.updateDiarist(updateDiaristDto =
                updatedDiaristDto
            )
            Log.i("USECASE", response.toString())
            Log.i("JSON-UPDATE", Gson().toJson(updatedDiaristDto))
            emit(Resource.Success(data = response))
        }  catch (e: HttpException){

            e.localizedMessage?.let { Log.i("DIARISt", it) }
            emit(Resource.Error(e.localizedMessage?:"Algum erro ocorreu."))
        }   catch (e: IOException){
            Log.i("DIARISt", e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO.")
            emit(Resource.Error(e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO."))
        }
    }
}
fun makePhoneExample(){

}


