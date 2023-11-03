package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address

sealed class AddressFormEvent {
    data class CepChanged(val newCep: String): AddressFormEvent()
    data class NumberChanged(val newNumber: String): AddressFormEvent()
    data class ReceivedCepFromApi(val completeAddress: AddressFormState): AddressFormEvent()
}




