package br.senai.sp.jandira.limpeanapp.registration

sealed class RegistrationEvent {
    data class NameChanged(val newName: String) : RegistrationEvent()
    data class CPFChanged(val newCPF: String) : RegistrationEvent()
    data class RGChanged(val newRG: String) : RegistrationEvent()
    data class TelephoneChanged(val newTelephone: String) : RegistrationEvent()
    data class DateOfBirthChanged(val newDateOfBirth: String) : RegistrationEvent()
    data class GenderSelected(val newGender: String) : RegistrationEvent()
    object SubmitClicked : RegistrationEvent()
}