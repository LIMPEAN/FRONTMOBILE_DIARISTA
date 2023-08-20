package br.senai.sp.jandira.limpeanapp.registration.person

sealed class RegistrationPersonEvent {
    data class NameChanged(val name: String) : RegistrationPersonEvent()
}
