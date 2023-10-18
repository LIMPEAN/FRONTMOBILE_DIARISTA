package br.senai.sp.jandira.limpeanapp.login.presentation


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.login.domain.AuthResult
import br.senai.sp.jandira.limpeanapp.login.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set


    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()
    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EmailChanged -> {
                state = state.copy(email = event.value)
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.value)
            }
            is LoginEvent.Login -> {
                login()
            }

            LoginEvent.LoginWithGoogle -> TODO()
        }
    }
    private fun login(){
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = authRepository.login(
                email = state.email,
                password = state.password
            )
            state = state.copy(isLoading = false)
            resultChannel.send(result)

        }
    }



}

