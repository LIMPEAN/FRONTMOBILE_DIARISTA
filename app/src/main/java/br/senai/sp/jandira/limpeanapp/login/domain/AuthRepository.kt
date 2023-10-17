package br.senai.sp.jandira.limpeanapp.login.domain

import br.senai.sp.jandira.limpeanapp.login.data.api.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String) : AuthResult<Unit>

}