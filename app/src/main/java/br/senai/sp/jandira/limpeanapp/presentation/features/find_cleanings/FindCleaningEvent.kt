package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

sealed class FindCleaningEvent {
    data class OnAcceptClick(val cleaning: Cleaning): br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent()
    data class OnCleaningInfoClick(val cleaning: Cleaning): br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent()
    data class OnCleaningClick(val cleaning: Cleaning): br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent()
}