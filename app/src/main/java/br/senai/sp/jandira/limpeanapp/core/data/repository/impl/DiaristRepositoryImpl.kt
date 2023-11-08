package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import br.senai.sp.jandira.limpeanapp.core.data.mapper.getRequestApi
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toRequestApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.repository.DiaristRepository
import br.senai.sp.jandira.limpeanapp.di.AuthInterceptor
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.diaristTest
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.jvm.Throws

class DiaristRepositoryImpl @Inject  constructor(
    private val api: AuthApi
) : DiaristRepository {
    override fun getDiarists(): Flow<List<Diarist>> {
        TODO("Not yet implemented")
    }

//    override suspend fun getDiarist(diarist: Diarist): Diarist? {

//        try {
//            val response = api.getDiarist(diarist.getRequestApi())
//            if (response) {
//                val diaristDto = response.body()
//                return diaristDto?.toDiarist() // Converta o DTO para o modelo Diarist se necessário
//            } else {
//                // Se a chamada não for bem-sucedida, você pode lançar uma exceção ou retornar null, dependendo do seu caso
//                return null
//            }
//        } catch (e: Exception) {
//            // Trate exceções aqui, se necessário
//            return null
//        }
//    }


    override suspend fun getDiaristByPhoneAndEmail(phone: String, email: String): Diarist? {
        TODO("NOT yet implemented")
    }
    @Throws(Exception::class)
    override suspend fun insertDiarist(diarist: Diarist) {

        val response = api.register(diarist.toRequestApi())

        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            val serialize = Gson().fromJson(errorBody, BaseDto::class.java)
            throw Exception(serialize.message)
        }

//        val errorResponse = Gson().fromJson(response.errorBody()?.string(), ApiResponse::class.java)
//        val errorMessage = errorResponse?.message



//        if(response.code() > 400){
//
//        }
    }


    override suspend fun deleteDiarist(diarist: Diarist) {
        TODO("Not yet implemented")
    }
}
data class ErrorServerException(override val message : String) : Exception(message)