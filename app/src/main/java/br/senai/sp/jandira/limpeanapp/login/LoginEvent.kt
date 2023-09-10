package br.senai.sp.jandira.limpeanapp.login

sealed class LoginEvent {
   data class NameChanged(val newName: String) : LoginEvent()
   data class CPFChanged(val newCPF: String) : LoginEvent()
   data class RGChanged(val newRG: String) : LoginEvent()
   data class TelephoneChanged(val newTelephone: String) : LoginEvent()
   data class DateOfBirthChanged(val newDateOfBirth: String) : LoginEvent()
   data class GenderSelected(val newGender: String) : LoginEvent()
   object SubmitClicked : LoginEvent()
}