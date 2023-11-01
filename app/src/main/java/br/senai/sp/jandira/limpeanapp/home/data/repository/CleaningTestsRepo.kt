package br.senai.sp.jandira.limpeanapp.home.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Shower
import br.senai.sp.jandira.limpeanapp.home.presentation.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.home.presentation.cleaning.components.QuantityRoomsCategory


val quantityRooms = listOf(
    QuantityRoomsCategory(
        name = "Quarto",
        icon = Icons.Outlined.Bed,
        quantity = 3
    ),
    QuantityRoomsCategory(
        name = "Banheiro",
        icon = Icons.Outlined.Shower,
        quantity = 2
    ),
    QuantityRoomsCategory(
        name = "Sala",
        icon = Icons.Outlined.Chair,
        quantity = 3
    ),
    QuantityRoomsCategory(
        name = "Garagem",
        icon = Icons.Filled.Garage,
        quantity = null
    )
)
val quantityRoomsMayara = listOf(
    QuantityRoomsCategory(
        name = "Quarto",
        icon = Icons.Outlined.Bed,
        quantity = 1
    ),
    QuantityRoomsCategory(
        name = "Banheiro",
        icon = Icons.Outlined.Shower,
        quantity = 1
    ),
    QuantityRoomsCategory(
        name = "Sala",
        icon = Icons.Outlined.Chair,
        quantity = 1
    ),
    QuantityRoomsCategory(
        name = "Garagem",
        icon = Icons.Filled.Garage,
        quantity = 1
    )
)

val cleanings = listOf(
    CleaningCardState(
        "Felipe",
        200.00,
        "Cotia, SP",
        quantityRooms = quantityRooms,
    ),
    CleaningCardState(
        "Mayara",
        100.00,
        "Jandira, SP",
        quantityRooms = quantityRoomsMayara,
    )
)