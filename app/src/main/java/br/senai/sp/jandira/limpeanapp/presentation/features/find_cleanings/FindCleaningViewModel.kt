package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class FindCleaningViewModel @Inject  constructor(
    private val repository : CleaningRepository,
    private val sessionCache : SessionCache
) : ViewModel() {


    var selectedCleaning by mutableStateOf(Cleaning())
        private set

    val cleanings = repository.getScheduledCleanings()




    var emailUser by mutableStateOf("Carregando")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()



    init {
        runBlocking {
           emailUser = sessionCache.getActiveSession()?.user?.email.toString()
        }
        viewModelScope.launch {

        }
    }


    fun onEvent(event : br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent){
        when(event){
            is br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent.OnCleaningClick -> {
//
                selectedCleaning = event.cleaning
                sendUiEvent(UiEvent.ShowBottomSheet)
            }
            is br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent.OnAcceptClick -> {
                TODO()
            }
            is br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent.OnCleaningInfoClick -> {
                TODO()
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
