package br.senai.sp.jandira.limpeanapp.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.registration.person.RegistrationPersonEvent

class LoginViewModel(
    private val repo : InMemoryUserTypeRepository = InMemoryUserTypeRepository()
) : ViewModel() {

    var state by mutableStateOf(LoginState(typeUser = getUserTypes()[1],userTypes = getUserTypes()))

    fun onEvent(event : LoginEvent){
        when(event){
            is LoginEvent.selectedChange -> {
                state = state.copy(typeUser = event.type)
            }
        }
    }
     private fun getUserTypes() : List<UserType> {
        return this.repo.getAll()
    }

}
data class LoginState(
    val typeUser: UserType?= null,
    val userTypes: List<UserType>
)
