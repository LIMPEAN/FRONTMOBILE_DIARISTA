package br.senai.sp.jandira.limpeanapp.feature_authentication.data.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.mapper.toSession
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.dto.AuthRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.AuthRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import retrofit2.HttpException
import javax.inject.Inject
private const val AUTH_REPOSITORY_IMPL = "auth-repository-impl"
class AuthRepositoryImpl @Inject constructor(
    private val api : AuthApi,
    private val sessionCache : SessionCache,
) : AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.login(
                request = AuthRequest(
                    email = email,
                    password = password
                )
            )
            sessionCache.saveSession(response.toSession())
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            sessionCache.getActiveSession() ?: return AuthResult.Unauthorized()
            api.authenticate()
            AuthResult.Authorized(data = null)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}