package br.senai.sp.jandira.limpeanapp.login.domain

interface AuthRepository {
    suspend fun login(email: String, password: String) : AuthResult<Unit>

}