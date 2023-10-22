package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.RegisterEvent

sealed class AddressFormEvent {
    data class CepChanged(val newCep: String): AddressFormEvent()
    data class NumberChanged(val newNumber: String): AddressFormEvent()
    data class ReceivedCepFromApi(val completeAddress: AddressFormState): AddressFormEvent()
}




