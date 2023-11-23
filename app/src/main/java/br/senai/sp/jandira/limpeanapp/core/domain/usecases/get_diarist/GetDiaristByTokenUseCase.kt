package br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist.toDiarist
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
    private val diaristApi: DiaristApi
) {

    operator fun invoke() : Flow<Resource<Diarist>> = flow {
        try {
            emit(Resource.Loading())
            val diarist = diaristApi.getDiarist().data.toDiarist()
            Log.i("USECASE", diarist.toString())
            emit(Resource.Success(data = diarist))
        }  catch (e: HttpException){

            Log.i("DIARISt", e.message())
            emit(Resource.Error(e.localizedMessage?:"Algum erro ocorreu."))
        }   catch (e: IOException){
            Log.i("DIARISt", e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO.")
            emit(Resource.Error(e.localizedMessage?: "Algum erro ocorreu. OPERAÇÃO."))
        }
    }
}
