package br.senai.sp.jandira.limpeanapp.core.domain.usecases

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.assentment.CreateAssentmentDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist.toDiarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.repository.DiaristRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


fun getHourAndMinuteNow() : String{
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return  currentTime.format(formatter)
}
fun getDateFormattedNow() : String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
   return  currentDate.format(formatter)
}
class SendAssentment @Inject constructor(
    private val api : DiaristApi,
) {
    operator fun invoke(assentment: Assentment) : Flow<Resource<BaseResponseDto>> = flow {
        try {
            emit(Resource.Loading())
            val hour = getHourAndMinuteNow()
            val date = getDateFormattedNow()
            val result = api.sendAssentment(
                CreateAssentmentDto(
                    comment = assentment.comment,
                    date = date,
                    hour = hour,
                    personEvaluatedId = assentment.personEvaluatedId,
                    star = assentment.star,
                    typeUser = "diarist"
                )
            )
            emit(Resource.Success(data = result))
        }  catch (e: HttpException){

            Log.i("DIARISt", e.message())
            emit(Resource.Error(e.localizedMessage?:"Algum erro ocorreu."))
        }   catch (e: IOException){
            Log.i("DIARISt", e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO.")
            emit(Resource.Error(e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO."))
        }
    }
}