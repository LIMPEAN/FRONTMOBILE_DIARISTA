package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toRequestApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist.GetDiaristDTOX
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.toDiarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.repository.DiaristRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.jvm.Throws

class DiaristRepositoryImpl @Inject  constructor(
    private val api: AuthApi,
    private val diaristApi: DiaristApi
) : DiaristRepository {

    override suspend fun getDiaristByToken(): GetDiaristDTOX {
        val diarist =  diaristApi.getDiarist()
        Log.i("REPO", diarist.toString())
        return diarist
    }

    @Throws(Exception::class)
    override suspend fun insertDiarist(diarist: Diarist): BaseResponseDto {

        val response = api.register(diarist.toRequestApi())

        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            val serialize = Gson().fromJson(errorBody, BaseResponseDto::class.java)
            throw Exception(serialize.message)
        }
        return response.body()!!

    }


//    override suspend fun deleteDiarist(diarist: Diarist): BaseResponseDto {
//        return diaristApi.deleteDiarist()
//    }
}
data class ErrorServerException(override val message : String) : Exception(message)