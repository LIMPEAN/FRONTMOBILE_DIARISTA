package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Session
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.TokenResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.SessionCache
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class SessionCacheImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): SessionCache {
    companion object {
        val USER_PREFERENCES_KEY = stringPreferencesKey("user-preferences")
        val adapter = Gson()
    }

    override suspend fun saveSession(session: Session) {
        dataStore.edit {settings ->
            settings[USER_PREFERENCES_KEY] = adapter.toJson(session)
        }
    }

    override suspend fun getActiveSession(): Session? {
        val userPreferences =  dataStore.data.first()
        val userPreferencesInJson = userPreferences[USER_PREFERENCES_KEY]
        return adapter.fromJson(userPreferencesInJson, Session::class.java)
    }

    override suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(USER_PREFERENCES_KEY)
        }
    }


}