package br.senai.sp.jandira.limpeanapp.home.cleaning.components

import androidx.compose.runtime.Composable

data class CleaningCardState(
    val nameClient: String,
    val servicePrice: Double,
    val local: String,
    val quantityRooms: List<QuantityRoomsCategory>
)
