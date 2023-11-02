package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models

import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AddressCleaningState

data class Address(
    val cep: String = "",
    val street: String = "",
    val district: String = "",
    val city: String = "",
    val state: String = "",
    val number: String = "",
    val complement: String? = null
){
    fun inCleaningCard() : String{
        return "${this.street}, ${this.number} - ${this.city}, ${this.city}"
    }

    fun toAddressCleaningState(): AddressCleaningState {
       return AddressCleaningState(
           street = this.street,
           city = this.city,
           state = this.state,
           complement = this.complement,
           district = this.district
       )
    }
}

