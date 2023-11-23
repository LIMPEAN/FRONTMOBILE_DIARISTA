package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

data class Address(
    val cep: String,
    val city: String,
    val state: String
)

fun Address.toAddress() : br.senai.sp.jandira.limpeanapp.core.domain.models.Address {
    return br.senai.sp.jandira.limpeanapp.core.domain.models.Address(
        cep, city,state
    )
}