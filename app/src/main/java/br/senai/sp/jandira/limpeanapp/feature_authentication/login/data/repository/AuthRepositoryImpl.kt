package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.repository


import android.util.Log
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.AuthRequest
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.AuthRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenResult
import retrofit2.HttpException
import javax.inject.Inject

private const val AUTH_REPOSITORY_IMPL = "auth-repository-impl"
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
            Log.e("api-response", response.toString())
            tokenManager.saveToken(response.toString())
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