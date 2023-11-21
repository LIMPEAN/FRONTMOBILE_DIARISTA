package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.AddressCleaningState


data class Address(
    val cep: String = "",
    val street: String = "",
    val district: String = "",
    val city: String = "",
    val state: String = "",
    val number: String = "",
    val complement: String? = null
)
fun Address.inCleaningCard() : String{
    return "${this.street}, ${this.number} - ${this.city}, ${this.city}"
}

fun Address.toAddressCleaningState(): AddressCleaningState {
    return AddressCleaningState(
        street = this.street,
        city = this.city,
        state = this.state,
        complement = this.complement,
        district = this.district
    )
}