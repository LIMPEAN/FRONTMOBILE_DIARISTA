package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.login



data class LoginState(
    val isLoading : Boolean = false,
    val userType : String? = null,
    val email : String = "",
    val emailError: String? = null,
    val password : String = "",
    val passwordError: String? = null,
    val message : String? = null,
    val isLogged : Boolean = false
)


