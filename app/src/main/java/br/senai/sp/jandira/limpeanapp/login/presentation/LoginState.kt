package br.senai.sp.jandira.limpeanapp.login.presentation



data class LoginState(
    val isLoading : Boolean = false,
    val email : String = "",
    val emailError: String? = null,
    val password : String = "",
    val passwordError: String? = null,
    val message : String? = null,
    val isLogged : Boolean = false
)

