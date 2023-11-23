package br.senai.sp.jandira.limpeanapp.core.domain.usecases.services



import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StartServiceUseCase @Inject constructor(
    private val repository : CleaningRepository
){
    operator fun invoke(idService : Number) : Flow<Resource<Token>> = flow {
        try {
            emit(Resource.Loading())
            val token = repository.startService(id = idService)
            emit(Resource.Success(
                data = Token(
                    status = token.status,
                    token = token.token
                )
            ))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Erro de internet inesperado."))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?: "Erro de operação inesperado."))
        }
    }
}