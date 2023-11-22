package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.util.Log
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
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.AcceptServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FindCleaningViewModel @Inject  constructor(
    private val getOpenServicesUseCase: GetOpenServicesUseCase,
    private val getDiaristByToken : GetDiaristByTokenUseCase,
    private val acceptServiceUseCase : AcceptServiceUseCase
) : ViewModel() {


    private val _state = mutableStateOf(FindCleaningState())
    val state : State<FindCleaningState> = _state

    private val _getDiaristState = mutableStateOf(GetDiaristState())
    val getDiaristState = _getDiaristState

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _selectedCleaning = mutableStateOf(Cleaning())
    val selectedCleaning = _selectedCleaning

    private lateinit var refreshJob : Job
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
        refreshJob = getOpenServicesUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = FindCleaningState(
                        openServices = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = FindCleaningState(
                        error = result.message?: "Um erro inesperado aconteceu."
                    )
                }
                is Resource.Loading -> {
                    _state.value = FindCleaningState(
                        isLoadingCleanings = true,
                    )
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
                _selectedCleaning.value = event.cleaning
            }
            is FindCleaningEvent.OnAcceptClick -> {
                acceptService(event.cleaning)
            }
            is FindCleaningEvent.OnCleaningInfoClick -> {
                _selectedCleaning.value = event.cleaning
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    private fun acceptService(cleaningAccept : Cleaning){
        acceptServiceUseCase(id = cleaningAccept.id!!)
            .onEach {result ->
                when(result){
                    is Resource.Success -> {
                        sendUiEvent(UiEvent.ShowToast(
                            result.message?: "Operação bem sucedida."
                        ))
                    }
                    is Resource.Error -> {
                        sendUiEvent(UiEvent.ShowToast(
                            message = result.message?: "Erro ao aceitar serviço"
                        ))
                        getServices()
                    }
                    is Resource.Loading -> {
                        _state.value = FindCleaningState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }


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

//    }

    private fun getDiarist(){
        val teste = getDiaristByToken().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _getDiaristState.value = GetDiaristState(
                        diarist = result.data ?: Diarist("Teste")
                    )
                    Log.i("RESULT-SUCCESS", result.data.toString() )
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(result.message?: "Erro ao carregar perfil."))
                }
                is Resource.Loading -> {
                    _getDiaristState.value = GetDiaristState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
