package br.senai.sp.jandira.limpeanapp.registration.person

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserState

class RegistrationPersonViewModel(
    private val userState : RegistrationUserState = RegistrationUserState()
) : ViewModel() {
    var state: RegistrationPersonState = RegistrationPersonState()

    fun onEvent(event : RegistrationPersonEvent){
        when(event){
            is RegistrationPersonEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
        }
    }


}