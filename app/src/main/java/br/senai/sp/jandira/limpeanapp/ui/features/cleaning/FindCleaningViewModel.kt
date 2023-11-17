package br.senai.sp.jandira.limpeanapp.ui.features.cleaning

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import br.senai.sp.jandira.limpeanapp.navigation.HomeRoute
import br.senai.sp.jandira.limpeanapp.ui.features.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
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


    fun onEvent(event : FindCleaningEvent){
        when(event){
            is FindCleaningEvent.OnCleaningClick -> {
//
                selectedCleaning = event.cleaning
                sendUiEvent(UiEvent.ShowBottomSheet)
            }
            is FindCleaningEvent.OnAcceptClick -> {
                TODO()
            }
            is FindCleaningEvent.OnCleaningInfoClick -> {
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
