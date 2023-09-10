package br.senai.sp.jandira.limpeanapp.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private val _registrationState = mutableStateOf(RegistrationState())
    val registrationState: MutableState<RegistrationState> = _registrationState


    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.NameChanged -> {
                _registrationState.value = _registrationState.value.copy(name = event.newName)
            }
            is RegistrationEvent.CPFChanged -> {
                _registrationState.value = _registrationState.value.copy(cpf = event.newCPF)
            }
            is RegistrationEvent.RGChanged -> {
                _registrationState.value = _registrationState.value.copy(rg = event.newRG)
            }
            is RegistrationEvent.TelephoneChanged -> {
                _registrationState.value = _registrationState.value.copy(telephone = event.newTelephone)
            }
            is RegistrationEvent.DateOfBirthChanged -> {
                _registrationState.value = _registrationState.value.copy(dateOfBirth = event.newDateOfBirth)
            }
            is RegistrationEvent.GenderSelected -> {
                _registrationState.value = _registrationState.value.copy(gender = event.newGender)
            }
            // Handle other registration events as needed
            else -> {}
        }
    }
}