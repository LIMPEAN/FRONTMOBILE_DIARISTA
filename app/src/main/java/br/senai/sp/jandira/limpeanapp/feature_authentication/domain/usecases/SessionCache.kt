package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases

import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Session

interface SessionCache {
    suspend fun saveSession(session: Session)

    suspend fun getActiveSession(): Session?

    suspend fun clearSession()
}