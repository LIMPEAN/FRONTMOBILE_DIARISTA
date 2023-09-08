package br.senai.sp.jandira.limpeanapp.login

sealed class LoginEvent {
   data class SelectedChange(val type: UserType) : LoginEvent()
}