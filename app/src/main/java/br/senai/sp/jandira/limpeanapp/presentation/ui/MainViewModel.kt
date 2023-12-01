package br.senai.sp.jandira.limpeanapp.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.limpean.AuthApi
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.repository.AuthRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: AuthRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged

    init {
        _isLoading.value = false
    }
    fun authenticate(){
        viewModelScope.launch {
            try {
                _isLogged.value = repository.authenticate() == AuthResult.Authorized(null)
            } catch (e: Exception) {
                // Tratar o erro aqui (exibir mensagem, fazer log, etc.)
                _isLogged.value = false
            }
        }
    }


}