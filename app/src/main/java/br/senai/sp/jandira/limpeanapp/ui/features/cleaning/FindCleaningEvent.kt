package br.senai.sp.jandira.limpeanapp.ui.features.cleaning

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

sealed class FindCleaningEvent {
    data class OnAcceptClick(val cleaning: Cleaning): FindCleaningEvent()
    data class OnCleaningInfoClick(val cleaning: Cleaning): FindCleaningEvent()
    data class OnCleaningClick(val cleaning: Cleaning): FindCleaningEvent()
}