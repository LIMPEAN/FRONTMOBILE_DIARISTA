package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

data class SettingsState(
    val isLoading : Boolean = false,
    val showHistory : Boolean = false,
    val histories : List<Cleaning>? = null,
    val error : String? = null
)
