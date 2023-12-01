package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetInvitesUseCase @Inject constructor(
    private val repository: CleaningRepository
){
    operator fun invoke() : Flow<Resource<List<Cleaning>>> = flow {
        try {
            emit(Resource.Loading())
            val invites = repository.getInvites()
            emit(Resource.Success(invites))
        } catch (e: HttpException){
            if (e.code() == 404){
                emit(Resource.Success(data = emptyList()))
            } else{
                emit(Resource.Error( e.message() ?: "Um erro desconhecido ocorreu. INTERNET."))
            }

        } catch (e : IOException){
            emit(Resource.Error( "Não foi possível conectar ao servidor. Verifique sua conexão de internet."))
        }

    }
}