package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase

sealed class RegisterResult<T> {
    object Successful : RegisterResult<Unit>()
    data class Error(val message: String) : RegisterResult<Unit>()
}
