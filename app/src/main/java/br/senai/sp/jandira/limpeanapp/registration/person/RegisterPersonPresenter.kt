package br.senai.sp.jandira.limpeanapp.registration.person

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.registration.RegistrationEvent

class RegisterPersonPresenter : ViewModel() {

    private val _personState = mutableStateOf(RegistrationState())
    val personState: MutableState<RegistrationState> = _personState


    fun getPersonState() : {
        return _personState
    }

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.NameChanged -> {
                _personState.value = _personState.value.copy(name = event.newName)
            }
            is RegistrationEvent.CPFChanged -> {
                _personState.value = _personState.value.copy(cpf = event.newCPF)
            }
            is RegistrationEvent.RGChanged -> {
                _personState.value = _personState.value.copy(rg = event.newRG)
            }
            is RegistrationEvent.TelephoneChanged -> {
                _personState.value = _personState.value.copy(telephone = event.newTelephone)
            }
            is RegistrationEvent.DateOfBirthChanged -> {
                _personState.value = _personState.value.copy(dateOfBirth = event.newDateOfBirth)
            }
            is RegistrationEvent.GenderSelected -> {
                _personState.value = _personState.value.copy(gender = event.newGender)
            }
            // Handle other registration events as needed
            else -> {}
        }
    }
}