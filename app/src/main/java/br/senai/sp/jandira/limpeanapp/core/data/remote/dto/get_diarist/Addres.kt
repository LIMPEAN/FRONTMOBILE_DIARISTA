package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist

data class Addres(
    val cep: String,
    val city: String,
    val complement: String,
    val district: String,
    val numberHouse: String,
    val publicPlace: String,
    val state: String
)