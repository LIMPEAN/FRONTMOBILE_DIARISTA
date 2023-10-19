package br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.api

data class AuthRequest(
    val typeUser: String = "diarist",
    val email : String,
    val password: String
)
