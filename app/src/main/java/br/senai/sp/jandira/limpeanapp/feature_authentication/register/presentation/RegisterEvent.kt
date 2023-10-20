package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.LoginEvent

sealed class RegisterEvent {
    data class NameChanged(val value: String) : RegisterEvent()
    data class CpfChanged(val value: String) : RegisterEvent()
    data class PhoneChanged(val value: String) : RegisterEvent()
    data class EmailChanged(val value: String) : RegisterEvent()
    data class PasswordChanged(val value: String) : RegisterEvent()
    data class RepeatedPasswordChanged(val value: String) : RegisterEvent()
    data class DateOfBirthChanged(val value: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()

}