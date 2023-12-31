package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data

import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.roomTypes
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningCardState


val quantityRooms = listOf(
    RoomQuantity(
       roomTypes[7],
        quantity = 3
    ),
    RoomQuantity(
        roomTypes[1],
        quantity = 2
    ),
    RoomQuantity(
        roomTypes[2],
        quantity = 3
    ),
)
val quantityRoomsMayara = listOf(
    RoomQuantity(
        roomTypes[6],
        quantity = 2
    ),
    RoomQuantity(
        roomTypes[1],
        quantity = 1
    ),
    RoomQuantity(
        roomTypes[4],
        quantity = 3
    ),
)

val cleanings = listOf(
    CleaningCardState(
        1,
        "Felipe",
        200.00,
        "Cotia, SP",
        quantityRooms = quantityRooms,
    ),
    CleaningCardState(
        2,
        "Mayara",
        100.00,
        "Jandira, SP",
        quantityRooms = quantityRoomsMayara,
    )
)