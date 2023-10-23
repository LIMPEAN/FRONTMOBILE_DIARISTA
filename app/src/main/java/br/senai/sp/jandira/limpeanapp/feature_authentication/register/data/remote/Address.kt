package br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.remote

data class AddressRequest(
    val state: Int,
    val city: String,
    val cep: String,
    val publicPlace: String,
    val complement: String?,
    val district: String,
    val houseNumber: String
)
