package br.senai.sp.jandira.limpeanapp.core.domain.usecases.services

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.UpdateStatusDto
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AcceptServiceUseCase @Inject constructor(
    private val repository: CleaningRepository
) {
    operator fun invoke(id: Number) : Flow<Resource<UpdateStatusDto>> = flow{
        try {
            emit(Resource.Loading())
            val updateStatusDto = repository.acceptService(id)
            emit(Resource.Success(updateStatusDto))

        }  catch (e: HttpException){

            Log.i("DIARISt", e.message())
            emit(Resource.Error(e.localizedMessage?:"Algum erro ocorreu."))
        }   catch (e: IOException){
            Log.i("DIARISt", e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO.")
            emit(Resource.Error(e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO."))
        }
    }
}
