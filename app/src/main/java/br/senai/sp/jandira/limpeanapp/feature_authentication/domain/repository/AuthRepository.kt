package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository

import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String) : AuthResult<Unit>
    suspend fun authenticate() : AuthResult<Unit>
}