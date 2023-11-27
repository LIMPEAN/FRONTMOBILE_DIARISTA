package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist

data class FindCleaningState(
    val isLoading : Boolean = false,
    val isLoadingDiarist : Boolean = false,
    val isLoadingCleanings : Boolean = false,
    val openServices : List<Cleaning> = emptyList(),
    val message : String = "",
    val selectedCleaning : Cleaning = Cleaning(),
    val diarist: Diarist = Diarist(),
    val isShowBottomSheet : Boolean = false,
    val isShowAssentment: Boolean = false
)
data class GetDiaristState(
    val diarist : Diarist = Diarist(),
    val isLoading : Boolean = false,
    val error : String = "",
)