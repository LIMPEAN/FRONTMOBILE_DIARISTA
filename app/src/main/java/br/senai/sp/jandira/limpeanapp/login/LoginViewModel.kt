package br.senai.sp.jandira.limpeanapp.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.data.UserTypesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel() : ViewModel() {

    private val _state = MutableStateFlow(LoginState(
        userTypes = userTypesRepo,
        typeUser = userTypesRepo[0]
    ))
    val state = _state.asStateFlow()

    fun onEvent(event : LoginEvent){

    }
}
data class LoginState(
    val typeUser: UserType? = null,
    val userTypes: List<UserType> = emptyList()
)
private val userTypesRepo = UserTypesRepository.getAll()
