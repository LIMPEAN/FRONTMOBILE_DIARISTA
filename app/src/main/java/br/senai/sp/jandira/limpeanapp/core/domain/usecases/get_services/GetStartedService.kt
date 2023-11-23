package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStartedServiceUseCase @Inject constructor(
    private val repository : CleaningRepository
) {

    operator fun invoke() : Flow<Resource<Cleaning>> = flow {
        try {
            emit(Resource.Loading())
            val service = repository.getStartedService()
            emit(Resource.Success(
                data = service
            ))
        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?: "Erro de internet ocorreu."))
        } catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage?: "Erro de operação ocorreu."))
        }
    }
}