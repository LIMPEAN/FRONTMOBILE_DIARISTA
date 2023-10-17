package br.senai.sp.jandira.limpeanapp.login.data.api

data class AuthRequest(
    val typeUser: String = "diarist",
    val email : String,
    val password: String
)
