package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.dto

data class AuthResponse(
    val id: Number,
    val email: String,
    val token: String,
)
