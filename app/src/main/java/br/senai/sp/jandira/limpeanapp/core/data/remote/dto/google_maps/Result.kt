package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps

data class Result(
    val address_components: List<AddressComponent>,
    val formatted_address: String,
    val geometry: Geometry,
    val place_id: String,
    val postcode_localities: List<String>,
    val types: List<String>
)