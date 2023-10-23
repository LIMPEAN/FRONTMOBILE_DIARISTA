package br.senai.sp.jandira.limpeanapp.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toRequestApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote.RegisterRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.repository.DiaristRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
        TODO("Not yet implemented")
    }

    override suspend fun insertDiarist(diarist: Diarist) {
        val response = api.register(
            diarist = diarist.toRequestApi()
        )
        if(response.code() >= 400){
            throw Exception(
                message = response.message()
            )
        }
    }

    override suspend fun deleteDiarist(diarist: Diarist) {
        TODO("Not yet implemented")
    }
}
data class ErrorServerException(override val message : String) : Exception(message)