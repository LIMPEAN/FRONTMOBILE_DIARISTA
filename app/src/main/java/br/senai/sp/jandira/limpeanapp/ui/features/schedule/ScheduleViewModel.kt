package br.senai.sp.jandira.limpeanapp.ui.features.schedules

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.AddressCleaningState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.HomeInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.PrimordialInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeQuantityRooms
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SchedulesViewModel @Inject constructor(
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

    fun onEvent(event : ScheduleEvent){
        when(event){
            is ScheduleEvent.SeeCleanings -> {
               TODO()
            }
            is ScheduleEvent.SeeCleaningDetail -> {
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
    private fun createCleaningDetailsState(): CleaningDetailsState {
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
            CleaningSupportState(fakeQuestions, TypeCleaningEnum.PADRAO, rooms = fakeQuantityRooms)
        )
    }


}