package br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain

interface AuthRepository {
    suspend fun login(email: String, password: String) : AuthResult<Unit>

}