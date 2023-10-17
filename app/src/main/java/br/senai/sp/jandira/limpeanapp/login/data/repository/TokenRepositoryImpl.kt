package br.senai.sp.jandira.limpeanapp.login.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import br.senai.sp.jandira.limpeanapp.login.data.api.TokenResponse
import br.senai.sp.jandira.limpeanapp.login.domain.TokenRepository
import br.senai.sp.jandira.limpeanapp.login.domain.TokenResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

class TokenRepositoryImpl(
    private val dataStore : DataStore<Preferences>
) : TokenRepository {
    companion object {
        private val JWT_KEY = stringPreferencesKey("jwt")
        private val USER_PREFERENCES_KEY = stringPreferencesKey("user_preferences")
    }
    override suspend fun saveToken(token: String): TokenResult<Unit> {
        return try {
            dataStore.edit { preferences ->
                preferences[JWT_KEY] = token
            }
            TokenResult.SuccessfulSaveToken()
        }catch (e: Exception){
            TokenResult.UnknownError()
        }
    }

    override suspend fun getFirstToken(): TokenResult<Preferences> {
        return try {
            val firstToken = dataStore.data.first()
            TokenResult.FirstTokenFounded(
                data = firstToken
            )
        } catch (e: Exception){
            TokenResult.UnknownError()
        }
    }

    override suspend fun saveTokenWithUser(response: TokenResponse): TokenResult<Unit> {
        return try {
            val responseWasJson = Gson().toJson(response)
            dataStore.edit { preferences ->
                preferences[USER_PREFERENCES_KEY] = responseWasJson
            }
            TokenResult.SuccessfulSaveToken()
        }catch (e: Exception){
            TokenResult.UnknownError()
        }
    }
}