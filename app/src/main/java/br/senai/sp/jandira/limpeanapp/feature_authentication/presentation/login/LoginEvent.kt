package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.login

sealed class LoginEvent {
    data class EmailChanged(val value: String): LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
    object Login: LoginEvent()
    object LoginWithGoogle: LoginEvent()
}
