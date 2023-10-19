package br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain

import androidx.datastore.preferences.core.Preferences
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.TokenResponse

interface TokenRepository {
    suspend fun saveToken(token : String) : TokenResult<Unit>
    suspend fun getFirstToken(): String?
    suspend fun saveTokenWithUser(response : TokenResponse) : TokenResult<Unit>
}