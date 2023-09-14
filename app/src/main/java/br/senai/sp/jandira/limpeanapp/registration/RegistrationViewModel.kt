package br.senai.sp.jandira.limpeanapp.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.domain.UserType

import br.senai.sp.jandira.limpeanapp.login.InMemoryUserTypeRepository



class RegistrationViewModel : ViewModel() {
    private val _registrationState = mutableStateOf(RegistrationState(
        typeUser = "diarist",
        email = "email@gmail.com",
        address = Address(0,0,"", cep = "","", district = "test", houseNumber = "", complement = null),
        biography = "Teste",
        birthDate = "",
        idGender = 1,
        cpf = ""
    ))
    val registrationState: MutableState<RegistrationState> = _registrationState

    private val _userType = mutableStateOf<UserType?>(null)
    val userType: State<UserType?> = _userType

    // Function to set the UserType
    fun setUserType(userType: UserType) {
        _userType.value = userType
    }

    // Function to handle registration events and update the state

}
