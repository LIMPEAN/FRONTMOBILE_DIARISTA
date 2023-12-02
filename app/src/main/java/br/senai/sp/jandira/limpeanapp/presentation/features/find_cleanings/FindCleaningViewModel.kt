package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.FinishedService
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GetPropertiesForGoogleMapUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GoogleMapState
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.SendAssentment
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_diarist.GetDiaristByTokenUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetOpenServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetStartedServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.AcceptServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningListState
import br.senai.sp.jandira.limpeanapp.ui.components.dialog.AssentmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private val acceptServiceUseCase : AcceptServiceUseCase,
    private val getPropertiesForGoogleMapUseCase: GetPropertiesForGoogleMapUseCase,
    private val getStartedServiceUseCase: GetStartedServiceUseCase,
    private val finishedServiceUseCase : FinishedService,
    private val sendAssentmentUseCase: SendAssentment
) : ViewModel() {


    private val _googleMapState = mutableStateOf(GoogleMapState())
    val googleMapState : State<GoogleMapState> = _googleMapState

    private val _state = mutableStateOf(FindCleaningState())
    val state : State<FindCleaningState> = _state

    private val _getDiaristState = mutableStateOf(GetDiaristState())
    val getDiaristState = _getDiaristState

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _selectedCleaning = mutableStateOf(Cleaning())
    val selectedCleaning = _selectedCleaning

    private val _startedServices = mutableStateOf(CleaningListState())
    val startedServices : State<CleaningListState> = _startedServices

    private val _assentmentState = mutableStateOf(AssentmentState())
    val assentmentState : State<AssentmentState> = _assentmentState

    var message by mutableStateOf("")
        private set

    private lateinit var refreshJob : Job


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
                        message = result.message?: "Um erro inesperado aconteceu."
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
        getStartedServices()
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
            }

            is FindCleaningEvent.OnLoadingGoogleMap -> {
                getPropertiesForGoogleMap(event.cleaning)
            }

            is FindCleaningEvent.OnClickFinishedService -> {
                finishedService(event.cleaning)
            }

            is FindCleaningEvent.OnAssentment -> {
                sendAssentment(event.assentment)
            }
        }
    }

    private fun getAllServices(){
        getServices()
        getStartedServices()
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
                        _state.value = FindCleaningState(
                            message = result.message?: "Serviço aceito! Agendado com sucesso."
                        )
                    }
                    is Resource.Error -> {
                        _state.value = FindCleaningState(
                            message = result.message?: "Erro ao aceitar serviço."
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = FindCleaningState(isLoading = true)
                    }
                }
                getServices()
            }.launchIn(viewModelScope)
        }


    private fun getDiarist(){
        getDiaristByToken().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _getDiaristState.value = GetDiaristState(
                        diarist = result.data ?: Diarist("Teste")
                    )
                    Log.i("RESULT-SUCCESS", result.data.toString() )
                }
                is Resource.Error -> {
                    _getDiaristState.value = GetDiaristState(
                        error = result.message?: "Erro ao pegar diarista."
                    )
                }
                is Resource.Loading -> {
                    _getDiaristState.value = GetDiaristState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPropertiesForGoogleMap(cleaning: Cleaning){
        getPropertiesForGoogleMapUseCase(cleaning).onEach {result ->
            when(result){
                is Resource.Success ->{
                    _googleMapState.value = result.data?: GoogleMapState()
                }

                is Resource.Error -> {
                    _googleMapState.value = GoogleMapState()
                    _state.value = FindCleaningState(
                        message = "Erro ao carregar mapa. ${result.message}"
                    )
                }
                is Resource.Loading -> {
                    _googleMapState.value = GoogleMapState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStartedServices(){
        getStartedServiceUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _startedServices.value = CleaningListState(
                        cleanings = result.data?.let {
                            listOf(it)
                        } ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = FindCleaningState(
                        message = result.message?: "Um erro inesperado aconteceu."
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

    private fun finishedService(cleaning: Cleaning){
        finishedServiceUseCase(cleaning.id!!).onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = FindCleaningState(
                        message = "Finalizado com sucesso!",
                        isShowAssentment = true
                    )
                    getAllServices()
                }
                is Resource.Error -> {
                    _state.value = FindCleaningState(
                        message = result.message?: "Algum erro ocorreu."
                    )
                    getAllServices()
                }
                is Resource.Loading -> {
                    _state.value = FindCleaningState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendAssentment(assentmentState : AssentmentState){
        val assentment = Assentment(
            comment = assentmentState.comment,
            personEvaluatedId = assentmentState.client.id?.toInt()!!,
            star = assentmentState.stars.toInt()
        )
        sendAssentmentUseCase(assentment).onEach {result->
            when(result){
                is Resource.Success -> {
                    _assentmentState.value = AssentmentState(
                        message = "Avaliado com Sucesso!",
                    )
                }
                is Resource.Error -> {
                    _assentmentState.value = AssentmentState(
                        message = result.data?.message?.message?: "Erro ao avaliar cliente."
                    )
                }
                is Resource.Loading -> {
                    _assentmentState.value = AssentmentState(
                        isLoading = true
                    )
                }
            }
            getAllServices()
        }.launchIn(viewModelScope)
    }
}
