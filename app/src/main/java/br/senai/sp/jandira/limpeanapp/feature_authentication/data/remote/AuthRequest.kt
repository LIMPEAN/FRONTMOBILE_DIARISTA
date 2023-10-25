package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote

data class AuthRequest(
    val typeUser: String = "diarist",
    val email : String,
    val password: String
)
