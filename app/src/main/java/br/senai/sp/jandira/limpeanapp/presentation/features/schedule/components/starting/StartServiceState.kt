package br.senai.sp.jandira.limpeanapp.presentation.features.schedule.components.starting

data class StartServiceState(
    val isLoading : Boolean = false,
    val message : String = "",
    val token : String = "",
    val isTokenGenerate :  Boolean = false
)
