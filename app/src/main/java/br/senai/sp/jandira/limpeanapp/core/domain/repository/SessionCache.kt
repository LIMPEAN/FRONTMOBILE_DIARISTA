package br.senai.sp.jandira.limpeanapp.core.domain.repository

import br.senai.sp.jandira.limpeanapp.core.domain.models.Session

interface SessionCache {
    suspend fun saveSession(session: Session)

    suspend fun getActiveSession(): Session?

    suspend fun clearSession()
}