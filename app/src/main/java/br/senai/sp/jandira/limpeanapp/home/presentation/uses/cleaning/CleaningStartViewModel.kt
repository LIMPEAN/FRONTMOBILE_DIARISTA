package br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning

import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.data.repository.CleaningRepositoryImpl
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.home.data.repository.cleanings
import br.senai.sp.jandira.limpeanapp.home.data.repository.fakeQuantityRooms
import br.senai.sp.jandira.limpeanapp.home.data.repository.fakeQuestions
import br.senai.sp.jandira.limpeanapp.home.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.home.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.home.domain.models.Question
import br.senai.sp.jandira.limpeanapp.home.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.home.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AddressCleaningState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.HomeInfoState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.PrimordialInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class CleaningStartViewModel @Inject constructor(
    private val cleaningRepository : CleaningRepository
) : ViewModel() {


    var cleanings by mutableStateOf(fakeCleanings)
        private set

    var selectedCleaning by mutableStateOf<Cleaning?>(null)
        private set

    init {
        viewModelScope.launch {
            cleaningRepository.getScheduledCleanings().collect{
                cleanings = cleanings
            }
        }

    }

    fun onEvent(event : CleaningStartEvent){
        when(event){
            is CleaningStartEvent.SeeCleanings -> {
               TODO()
            }
            is CleaningStartEvent.SeeCleaningDetail -> {
//                getCleaningDetail(event.id)
            }

            else -> {}
        }
    }

    private fun getCleaningDetail(id: Number){
        val selectedCleaning =  cleanings.find {
            it.id == id
        }
        selectedCleaning?.let {
//            cleaning = it.toDetailsState()
        }
    }
    private fun createCleaningDetailsState(): CleaningDetailsState{
        return CleaningDetailsState(
            primordialInfo = PrimordialInfoState(
                price = 0.0,
                date = "",
                startTime = "",
            ),
            addressCleaning = AddressCleaningState(
                street = "",
                state = "",
                district = "",
                complement = null,
                city = ""
            ),
            AboutClientState(
                ClientInfoState(name = "", assentment = 0.0),
                HomeInfoState(typeHouse = "", name = "")
            ),
            CleaningSupportState(fakeQuestions,TypeCleaning.DEFAULT, rooms = fakeQuantityRooms)
        )
    }


}