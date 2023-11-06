package br.senai.sp.jandira.limpeanapp.ui.features.util

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()


    object ShowBottomSheet : UiEvent()
}