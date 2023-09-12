package br.senai.sp.jandira.limpeanapp.registration.person

sealed class PersonEvent {
    data class NameChanged(val newName: String) : PersonEvent()
    data class CPFChanged(val newCPF: String) : PersonEvent()
    data class IDChanged(val newID: String) : PersonEvent()
    data class TelephoneChanged(val newTelephone: String) : PersonEvent()
    data class DateOfBirthChanged(val newDateOfBirth: String) : PersonEvent()
    data class GenderSelected(val newGender: String) : PersonEvent()
    object SubmitClicked : PersonEvent()
}

sealed class RegisterPersonEvent {
    data class NameChanged(val newName: String) : RegisterPersonEvent()
    data class CPFChanged(val newCPF: String) : RegisterPersonEvent()
    data class RGChanged(val newRG: String) : RegisterPersonEvent()
    data class TelephoneChanged(val newTelephone: String) : RegisterPersonEvent()
    data class DateOfBirthChanged(val newDateOfBirth: String) : RegisterPersonEvent()
    data class GenderSelected(val newGender: String) : RegisterPersonEvent()
    object SubmitClicked : RegisterPersonEvent()
}