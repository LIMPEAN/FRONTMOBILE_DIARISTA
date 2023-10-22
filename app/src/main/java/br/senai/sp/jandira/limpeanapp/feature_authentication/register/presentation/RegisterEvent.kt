package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.LoginEvent

sealed class RegisterEvent {

    data class RepeatedPasswordChanged(val value: String) : RegisterEvent()
    data class DateOfBirthChanged(val value: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()

}