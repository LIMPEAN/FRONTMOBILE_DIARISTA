package br.senai.sp.jandira.limpeanapp.register

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegistrationFormEvent()

    data class PhoneChanged(val phone: String) : RegistrationFormEvent()

    object Next: RegistrationFormEvent()
}
