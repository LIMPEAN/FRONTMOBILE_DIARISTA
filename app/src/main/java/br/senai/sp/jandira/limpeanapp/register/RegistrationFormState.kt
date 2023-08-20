package br.senai.sp.jandira.limpeanapp.register

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val phone: String = "",
    val phoneError: String? = null
)
