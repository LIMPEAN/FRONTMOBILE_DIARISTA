package br.senai.sp.jandira.limpeanapp.domain

data class Address(
    val state: Number,
    val city : String,
    val cep: Number,
    val publicPlace: String,
    val complement: String
)