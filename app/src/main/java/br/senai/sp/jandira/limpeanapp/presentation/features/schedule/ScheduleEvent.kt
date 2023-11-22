package br.senai.sp.jandira.limpeanapp.presentation.features.schedule

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

sealed class ScheduleEvent {
    object SeeCleanings : ScheduleEvent()
    data class SeeCleaningDetail(val cleaning: Cleaning) : ScheduleEvent()
}
