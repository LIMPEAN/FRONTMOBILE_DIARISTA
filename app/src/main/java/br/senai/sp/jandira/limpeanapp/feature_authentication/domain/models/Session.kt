package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models

import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.AuthResponse

data class Session(
    val user: User,
    val token: String,
    val expiresAt: Long
)
data class User(
    val id: Number,
    val email: String
)


