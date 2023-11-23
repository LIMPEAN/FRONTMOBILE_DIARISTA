package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.google_maps

data class Geometry(
    val bounds: Bounds,
    val location: Location,
    val location_type: String,
    val viewport: Viewport
)