package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models

data class Address(
    val cep: String,
    val street : String,
    val district : String,
    val city : String,
    val state : String,
    val number : String,
    val complement : String?,
)
