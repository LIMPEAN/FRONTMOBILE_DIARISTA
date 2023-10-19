package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api.TokenResponse
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val dataStore : DataStore<Preferences>
) : TokenRepository {
    companion object {
        private val JWT_KEY = stringPreferencesKey("jwt")
        private val USER_PREFERENCES_KEY = stringPreferencesKey("user_preferences")
    }
    override suspend fun saveToken(token: String): TokenResult<Unit> {
        dataStore.edit {settings ->
            settings[JWT_KEY] = token
        }
        return TokenResult.SuccessfulSaveToken()

    }

    override suspend fun getFirstToken(): String? {
        val preferences =  dataStore.data.first()
        return preferences[JWT_KEY]
    }

    override suspend fun saveTokenWithUser(response: TokenResponse): TokenResult<Unit> {
        return try {
            val responseWasJson = Gson().toJson(response)
            dataStore.edit { preferences ->
                preferences[JWT_KEY] = responseWasJson
            }
            TokenResult.SuccessfulSaveToken()
        }catch (e: Exception){
            TokenResult.UnknownError()
        }
    }
}