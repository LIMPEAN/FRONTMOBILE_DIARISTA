package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

data class UpdatePriceDTO(
    val idService: Number,
    val newValue : String
)
// Formato new Value : "190,00" , tratar como double

