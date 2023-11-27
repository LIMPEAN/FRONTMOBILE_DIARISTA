package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

sealed class FindCleaningEvent {
    data class OnAcceptClick(val cleaning: Cleaning): FindCleaningEvent()
    data class OnCleaningInfoClick(val cleaning: Cleaning): FindCleaningEvent()
    data class OnCleaningClick(val cleaning: Cleaning): FindCleaningEvent()

    data class OnLoadingGoogleMap(val cleaning: Cleaning) : FindCleaningEvent()
    data class OnClickFinishedService(val cleaning: Cleaning) : FindCleaningEvent()
}