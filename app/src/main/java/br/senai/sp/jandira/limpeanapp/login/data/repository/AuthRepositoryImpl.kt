package br.senai.sp.jandira.limpeanapp.login.data.repository


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import br.senai.sp.jandira.limpeanapp.login.data.api.AuthApi
import br.senai.sp.jandira.limpeanapp.login.data.api.AuthRequest
import br.senai.sp.jandira.limpeanapp.login.data.api.AuthResult
import br.senai.sp.jandira.limpeanapp.login.domain.AuthRepository
import br.senai.sp.jandira.limpeanapp.login.domain.TokenRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api : AuthApi,
    private val tokenManager : TokenRepository,
) : AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.login(
                request = AuthRequest(
                    email = email,
                    password = password
                )
            )
            tokenManager.saveTokenWithUser(response)
            AuthResult.Authorized()
        } catch (e: HttpException){
            if(e.code() == 401){
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception){
            AuthResult.UnknownError()
        }
    }
}