package br.senai.sp.jandira.limpeanapp.core.presentation.util

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()


    object ShowBottomSheet : UiEvent()

    object ShowDialogLoading: UiEvent()

    object LoadindComplete: UiEvent()

    data class ShowToast(val message: String) : UiEvent()
}