package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps

data class GoogleMapsResultsDto(
    val results: List<Result>,
    val status: String
)