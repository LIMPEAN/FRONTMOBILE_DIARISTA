package br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning

import br.senai.sp.jandira.limpeanapp.home.domain.models.Cleaning

sealed class CleaningStartEvent {
    object SeeCleanings : CleaningStartEvent()
    data class SeeCleaningDetail(val cleaning: Cleaning) : CleaningStartEvent()
}
