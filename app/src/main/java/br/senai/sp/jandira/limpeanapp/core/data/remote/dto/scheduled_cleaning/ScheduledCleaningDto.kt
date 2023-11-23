package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

data class ScheduledCleaningDto(
    val data: List<ScheduleClient>,
    val status: Int
)