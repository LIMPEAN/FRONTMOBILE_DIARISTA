package br.senai.sp.jandira.limpeanapp.presentation.features.invites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.get_services.GetInvitesUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.services.SendPriceUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningListState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class InvitesViewModel  @Inject   constructor(
    private val getInvitesUseCase: GetInvitesUseCase,
    private val sendPriceUseCase: SendPriceUseCase
) : ViewModel(){

    private val _state = mutableStateOf(CleaningListState())
    val state : State<CleaningListState> = _state


    init {
        getInvites()
    }

    private fun getInvites(){
        getInvitesUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CleaningListState(
                        cleanings = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = CleaningListState(
                        message = result.message?: "Um erro inesperado aconteceu."
                    )
                }
                is Resource.Loading -> {
                    _state.value = CleaningListState(
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sendPrice(id: Number, price: Double){
        sendPriceUseCase(id,price).onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CleaningListState(
                        message = "DEU CERTO!!"
                    )
                }
                is Resource.Error -> {
                    _state.value = CleaningListState(
                        message = result.message?: "Um erro inesperado aconteceu."
                    )
                }
                is Resource.Loading -> {
                    _state.value = CleaningListState(
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}