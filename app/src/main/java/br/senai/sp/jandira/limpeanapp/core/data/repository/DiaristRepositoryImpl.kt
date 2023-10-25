package br.senai.sp.jandira.limpeanapp.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toRequestApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.BaseResponse
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.ErrorRepositoryException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.InvalidDiaristException
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import kotlin.jvm.Throws

class DiaristRepositoryImpl @Inject  constructor(
    private val api: AuthApi
) : DiaristRepository {
    override fun getDiarists(): Flow<List<Diarist>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDiaristById(id: Int): Diarist? {
        TODO("Not yet implemented")
    }

    override suspend fun getDiaristByPhoneAndEmail(phone: String, email: String): Diarist? {
        TODO("NOT yet implemented")
    }
    @Throws(Exception::class)
    override suspend fun insertDiarist(diarist: Diarist) {

        val response = api.register(diarist.toRequestApi())

        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            val serialize = Gson().fromJson(errorBody, BaseResponse::class.java)
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