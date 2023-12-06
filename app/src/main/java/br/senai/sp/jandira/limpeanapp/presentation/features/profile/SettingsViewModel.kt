package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.GetDiaristByTokenUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetFinishedServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class SettingsEvent {
    object OnClickHistory : SettingsEvent()
}

data class DiaristProfile(
    val isLoading : Boolean = false,
    val diarist : Diarist = Diarist(),
    val isEditMode : Boolean = false,
    val error : String? = null
)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getDiarist: GetDiaristByTokenUseCase,
    private val getHistories: GetFinishedServicesUseCase
)  : ViewModel(){


    var state by mutableStateOf(SettingsState())
        private set

    var profile by mutableStateOf(DiaristProfile())
        private set


    init {
        getDiaristFromApi()
    }
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

    private fun getDiaristFromApi(){
        getDiarist().onEach {result ->
            when (result){
                is Resource.Success -> {
                    profile = DiaristProfile(
                        diarist = result.data?: Diarist()
                    )
                }
                is Resource.Error -> {
                    profile = DiaristProfile(
                        error = result.message?: "Erro ao carregar diarista."
                    )
                }
                is Resource.Loading -> {
                    profile = DiaristProfile(
                        isLoading = true
                    )
                }
            }



        }.launchIn(viewModelScope)
    }


}