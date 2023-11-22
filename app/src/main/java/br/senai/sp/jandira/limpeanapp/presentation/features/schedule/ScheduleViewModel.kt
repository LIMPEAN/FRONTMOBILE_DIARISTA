package br.senai.sp.jandira.limpeanapp.presentation.features.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetScheduledServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningListState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.AddressCleaningState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.HomeInfoState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.PrimordialInfoState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data.fakeQuantityRooms
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data.fakeQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduledServices : GetScheduledServicesUseCase
) : ViewModel(){


    private val _state = mutableStateOf(CleaningListState())
    val state : State<CleaningListState> = _state

    private val _selectedCleaning = mutableStateOf(Cleaning())
    val selectedCleaning = _selectedCleaning

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    init {
        getScheduled()
    }


    private fun getScheduled(){
        getScheduledServices().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CleaningListState(
                        cleanings = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    sendUiEvent(
                        UiEvent.ShowToast(result.message?: "Um erro inesperado aconteceu.")
                    )

                }
                is Resource.Loading -> {
                    _state.value = CleaningListState(
                        isLoading = true,
                    )
                }
            }

        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onStartService(cleaning: Cleaning) {
        sendUiEvent(UiEvent.ShowToast(
            "Serviço iniciado. $cleaning"
        ))
    }
}