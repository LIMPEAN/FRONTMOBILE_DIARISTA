package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetFinishedServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class SettingsEvent {
    object OnClickHistory : SettingsEvent()
}


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getUserId: GetUserIdUseCase,
    private val getHistories: GetFinishedServicesUseCase
)  : ViewModel(){


    var state by mutableStateOf(SettingsState())
        private set

    fun onEvent(event : SettingsEvent) {
        when(event) {
            is SettingsEvent.OnClickHistory -> getHistory()
        }
    }
    private fun getHistory(){
        getHistories().onEach { result ->
            state = when(result){
                is Resource.Success -> {
                    state.copy(
                        showHistory = true,
                        histories = result.data
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    state.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}