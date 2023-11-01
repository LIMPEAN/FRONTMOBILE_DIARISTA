package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

sealed class RegisterEvent {

    data class RepeatedPasswordChanged(val value: String) : RegisterEvent()
    data class DateOfBirthChanged(val value: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()

}