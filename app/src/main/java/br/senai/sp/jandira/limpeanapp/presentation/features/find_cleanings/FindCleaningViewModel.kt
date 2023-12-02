package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

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
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.FindServicesUseCases
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetOpenServicesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetStartedServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.AcceptServiceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningListState
import br.senai.sp.jandira.limpeanapp.ui.components.dialog.AssentmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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



    var state by mutableStateOf(FindCleaningState())
        private set

    private val _getDiaristState = mutableStateOf(GetDiaristState())
    val getDiaristState = _getDiaristState

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _startedServices = mutableStateOf(CleaningListState())
    val startedServices : State<CleaningListState> = _startedServices

    private val _assentmentState = mutableStateOf(AssentmentState())
    val assentmentState : State<AssentmentState> = _assentmentState

    init {
        getDiarist()
        getAllServices()
    }

    fun onEvent(event : FindCleaningEvent){
        when(event){
            is FindCleaningEvent.OnAcceptClick -> {
                acceptService(event.cleaning)
            }

            is FindCleaningEvent.OnClickFinishedService -> {
                finishedService(event.cleaning)
            }

            is FindCleaningEvent.OnAssentment -> {
                sendAssentment(event.assentment)
            }
            else -> {}
        }
        getAllServices()
    }


    private fun getDiarist(){
        getDiaristByToken().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _getDiaristState.value = GetDiaristState(
                        diarist = result.data ?: Diarist("Teste")
                    )
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message =  result.message?: "Erro ao pegar diarista."
                    ))
                }
                is Resource.Loading -> {
                    _getDiaristState.value = GetDiaristState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getAllServices(){
        getOpenServices()
        getStartedServices()
    }
    private fun getOpenServices(){
        getOpenServicesUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    state = FindCleaningState(
                        openServices = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.message?: "Um erro ocorreu ao carregar serviços."
                    ))
                }
                is Resource.Loading -> {
                    state = FindCleaningState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStartedServices(){
        getStartedServiceUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _startedServices.value = CleaningListState(
                        cleanings = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.message?: "Um erro inesperado aconteceu."
                    ))
                }
                is Resource.Loading -> {
                    _startedServices.value  = CleaningListState(
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun acceptService(cleaningAccept : Cleaning){
        acceptServiceUseCase(id = cleaningAccept.id!!)
            .onEach {result ->
                when(result){
                    is Resource.Success -> {
                        sendUiEvent(UiEvent.ShowToast(
                            message = result.message?: "Serviço aceito com sucesso!"
                        ))
                    }
                    is Resource.Error -> {
                        sendUiEvent(UiEvent.ShowToast(
                            message = result.message?: "Erro ao aceitar serviço."
                        ))
                    }
                    is Resource.Loading -> {
                        sendUiEvent(UiEvent.ShowDialogLoading)
                    }
                }
            }.launchIn(viewModelScope)
        }
    private fun finishedService(cleaning: Cleaning){
        finishedServiceUseCase(cleaning.id!!).onEach {result ->
            when(result){
                is Resource.Success -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.message?: "Finalizado com sucesso!"
                    ))
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.message?: "Algum erro ocorreu."
                    ))
                }
                is Resource.Loading -> {
                   sendUiEvent(UiEvent.ShowDialogLoading)
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
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.message?: "Avaliado com sucesso!"
                    ))
                }
                is Resource.Error -> {
                    sendUiEvent(UiEvent.ShowToast(
                        message = result.data?.message?.message?: "Erro ao avaliar cliente."
                    ))

                }
                is Resource.Loading -> {
                    sendUiEvent(UiEvent.ShowDialogLoading)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
