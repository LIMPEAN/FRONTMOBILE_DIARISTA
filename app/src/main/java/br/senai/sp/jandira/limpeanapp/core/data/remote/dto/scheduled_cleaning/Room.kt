package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OtherHouses
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomType
import br.senai.sp.jandira.limpeanapp.core.domain.models.roomTypes

data class Room(
    val name: String,
    val quantity: Int
)

fun Room.toRoomQuantity() : RoomQuantity {
    val room = roomTypes.find { it.name == name }

    return RoomQuantity(
        roomType = room?: RoomType(
            icon = Icons.Default.OtherHouses,
            name = name,
        ),
        quantity = quantity
    )

}