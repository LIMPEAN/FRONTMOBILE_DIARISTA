package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist

data class FindCleaningState(
    val isLoading : Boolean = false,
    val isLoadingDiarist : Boolean = true,
    val openServices : List<Cleaning> = emptyList(),
    val error : String = "",
    val selectedCleaning : Cleaning = Cleaning(),
    val diarist: Diarist = Diarist(),
    val isShowBottomSheet : Boolean = false,
)
