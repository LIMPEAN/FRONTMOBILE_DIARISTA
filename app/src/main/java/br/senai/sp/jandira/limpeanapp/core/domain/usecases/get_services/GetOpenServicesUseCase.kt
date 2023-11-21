package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services

import br.senai.sp.jandira.limpeanapp.core.data.mapper.toCleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOpenServicesUseCase @Inject constructor(
    private val repository: CleaningRepository
){
    operator fun invoke() : Flow<Resource<List<Cleaning>>> = flow {
        try {
            emit(Resource.Loading())
            val openServices = repository
                .getOpenServices()
                .data.map { it.service.toCleaning() }
            emit(Resource.Success(openServices))
        } catch (e: HttpException){
            emit(Resource.Error( e.localizedMessage ?: "Um erro desconhecido ocorreu. INTERNET."))
        } catch (e : IOException){
            emit(Resource.Error( "Não foi possível conectar ao servidor. Verifique sua conexão de internet."))
        }

    }
}