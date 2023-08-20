package br.senai.sp.jandira.limpeanapp.registration.user

sealed class RegistrationUserEvent {
    data class EmailChanged(val email: String) : RegistrationUserEvent()
    data class PasswordChanged(val password: String) : RegistrationUserEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegistrationUserEvent()

    data class PhoneChanged(val phone: String) : RegistrationUserEvent()

    object Next: RegistrationUserEvent()
}
