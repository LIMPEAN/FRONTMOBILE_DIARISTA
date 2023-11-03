package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register

sealed class RegisterEvent {

    data class RepeatedPasswordChanged(val value: String) : RegisterEvent()
    data class DateOfBirthChanged(val value: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()

}