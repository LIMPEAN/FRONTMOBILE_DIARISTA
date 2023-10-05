package br.senai.sp.jandira.limpeanapp.authentication.login

data class LoginResponse(
    val status: Number,
    val message: String?,
    val id: Number?,
    val email : String?,
    val token : String?,
)
