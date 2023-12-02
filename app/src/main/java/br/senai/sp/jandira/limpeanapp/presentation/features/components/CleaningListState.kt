package br.senai.sp.jandira.limpeanapp.presentation.features.components

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning

data class CleaningListState(
    val listName : String = "Serviços",
    val cleanings : List<Cleaning> = emptyList(),
    val isLoading : Boolean = false,
    val message : String = ""
)
