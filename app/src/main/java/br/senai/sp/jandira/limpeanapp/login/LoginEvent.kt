package br.senai.sp.jandira.limpeanapp.login

sealed class LoginEvent {
   data class selectedChange(val type: UserType) : LoginEvent()
}