package br.senai.sp.jandira.limpeanapp.core.domain.usecases.update_diarist

data class Address(
    val cep: String? = null,
    val city: String? = null,
    val complement: String? = null,
    val district: String? = null,
    val houseNumber: String? = null,
    val publicPlace: String? = null,
    val state: String? = null
)