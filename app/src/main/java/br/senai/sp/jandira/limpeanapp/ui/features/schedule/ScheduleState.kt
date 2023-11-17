package br.senai.sp.jandira.limpeanapp.ui.features.schedule

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

data class ScheduleState(
    val isShowBottomSheet : Boolean = false,
    val cleaningSelected : Cleaning? = null
)
