package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)