package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.remote.TokenResponse
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.TokenResult

interface TokenRepository {
    suspend fun saveToken(token : String) : TokenResult<Unit>
    suspend fun getFirstToken(): String?
    suspend fun saveTokenWithUser(response : TokenResponse) : TokenResult<Unit>
}