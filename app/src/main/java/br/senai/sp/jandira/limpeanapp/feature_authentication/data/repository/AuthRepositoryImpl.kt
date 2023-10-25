package br.senai.sp.jandira.limpeanapp.feature_authentication.data.repository

import android.util.Log
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper.toSession
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.AuthRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.AuthRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.SessionCache
import retrofit2.HttpException
import javax.inject.Inject
private const val AUTH_REPOSITORY_IMPL = "auth-repository-impl"
class AuthRepositoryImpl @Inject constructor(
    private val api : AuthApi,
    private val sessionCache : SessionCache,
) : AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult<Unit> {
        return try {
            val tokenResponse = api.login(
                request = AuthRequest(
                    email = email,
                    password = password
                )
            )
            sessionCache.saveSession(session = tokenResponse.toSession())
            AuthResult.Authorized()
        } catch (e: HttpException){
            if(e.code() == 401){
                Log.e(AUTH_REPOSITORY_IMPL, e.message())
                AuthResult.Unauthorized()
            } else {
                Log.e(AUTH_REPOSITORY_IMPL, e.message())
                AuthResult.UnknownError()
            }
        } catch (e: Exception){
            Log.e(AUTH_REPOSITORY_IMPL, e.message.toString())
            AuthResult.UnknownError()
        }
    }
}