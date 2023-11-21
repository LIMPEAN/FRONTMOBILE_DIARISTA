package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.toDiarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.repository.DiaristRepository
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDiaristByTokenUseCase @Inject constructor(
    private val repository : DiaristRepository
) {

    operator fun invoke(token: String) : Flow<Resource<Diarist>> = flow {
        try {
            emit(Resource.Loading())
            val diarist = repository.getDiaristByToken(token).toDiarist()
            emit(Resource.Success(
                data = diarist
            ))
            Log.i("DIARISt", diarist.toString())
        }  catch (e: HttpException){
            emit(Resource.Error(
               e.localizedMessage?:"Algum erro ocorreu."
            ))
            Log.i("DIARISt", e.message())
        }   catch (e: IOException){
            emit(Resource.Error(
                e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO."
            ))
            Log.i("DIARISt", e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO.")
        }
    }
}
