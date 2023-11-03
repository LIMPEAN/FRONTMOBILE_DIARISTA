package br.senai.sp.jandira.limpeanapp.ui.features.schedules

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

sealed class CleaningStartEvent {
    object SeeCleanings : CleaningStartEvent()
    data class SeeCleaningDetail(val cleaning: Cleaning) : CleaningStartEvent()
}
