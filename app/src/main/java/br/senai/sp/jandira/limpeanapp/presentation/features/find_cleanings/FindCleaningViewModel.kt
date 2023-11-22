package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.domain.repository.SessionCache
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.GetDiaristByTokenUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetOpenServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FindCleaningViewModel @Inject  constructor(
    private val repository : CleaningRepository,
    private val sessionCache : SessionCache,
    private val getOpenServicesUseCase: GetOpenServicesUseCase,
    private val getDiaristByToken : GetDiaristByTokenUseCase
) : ViewModel() {


    private val _state = mutableStateOf(FindCleaningState())
    val state : State<FindCleaningState> = _state

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        getDiarist()
        routineGetServices()
    }

    private fun routineGetServices(){
        viewModelScope.launch {
            while (true){
                getServices()
                delay(5000)
            }
        }
    }
    private fun getServices(){
        getOpenServicesUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = FindCleaningState(openServices = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = FindCleaningState(error = result.message?: "Um erro inesperado aconteceu.")
                }
                is Resource.Loading -> {
                    _state.value = FindCleaningState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    init {
        getDiarist()
        getServices()
    }


    fun onEvent(event : FindCleaningEvent){
        when(event){
            is FindCleaningEvent.OnCleaningClick -> {
                showCleaningDetails(event.cleaning)
            }
            is FindCleaningEvent.OnAcceptClick -> {
                acceptService(event.cleaning)
            }
            is FindCleaningEvent.OnCleaningInfoClick -> {
                showCleaningDetails(event.cleaning)
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    private fun showCleaningDetails(selectedCleaning: Cleaning){
        _state.value = FindCleaningState(
            selectedCleaning = selectedCleaning,
            isShowBottomSheet = true
        )
    }
    private fun acceptService(cleaningAccept : Cleaning){
        _state.value = FindCleaningState(isLoading = true)
//        viewModelScope.launch{
//            _state.value = FindCleaningState(isLoading = true)
//            state.value.selectedCleaning.id?.let {
//                repository.acceptService(it)
//            }
//            isLoadingOperation = false
//            refreshCleanings()
//            sendUiEvent(UiEvent.ShowToast(
//                "Operação bem sucedida!"
//            ))
//        }

    }

    private fun getDiarist(){
        viewModelScope.launch {
            val session = sessionCache.getActiveSession()

            if (session != null) {
                getDiaristByToken(session.token)
                    .onEach {result ->
                        when(result){
                            is Resource.Success -> {
                                _state.value = FindCleaningState(
                                    isLoadingDiarist = false,
                                    diarist = result.data!!
                                )
                            }

                            is Resource.Error -> {
                                _state.value = FindCleaningState(
                                    isLoadingDiarist = false,
                                    error = result.message?: "Erro ao carregar perfil."
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = FindCleaningState(
                                    isLoadingDiarist = true
                                )
                            }
                        }
                    }
            }
        }
    }




}
