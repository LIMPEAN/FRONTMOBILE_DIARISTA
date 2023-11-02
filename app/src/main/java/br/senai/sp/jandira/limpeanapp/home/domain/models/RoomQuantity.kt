package br.senai.sp.jandira.limpeanapp.home.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Desk
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.LocalLaundryService
import androidx.compose.material.icons.filled.Yard
import androidx.compose.ui.graphics.vector.ImageVector
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.QuantityRoomsCategory

data class RoomQuantity(
    val roomType : RoomType,
    val quantity: Number?
){
    fun inCleaningCard(): QuantityRoomsCategory{
        var item =  roomTypes.find { it.name == this.roomType.name}
        if(item == null){
            item = RoomType(
                Icons.Default.House,
                "Padrão"
            )
        }
        return QuantityRoomsCategory(
            quantity = this.quantity,
            name = roomType.name,
            icon = item.icon
        )
    }
}
data class RoomType(
    val icon : ImageVector,
    val name: String,
)

val roomTypes = listOf(
    RoomType(Icons.Default.Bed, "Quarto"),
    RoomType(Icons.Default.Chair, "Sala"),
    RoomType(Icons.Default.Dining, "Cozinha"),
    RoomType(Icons.Default.Desk, "Escritório"),
    RoomType(Icons.Default.LocalLaundryService, "Lavanderia"),
    RoomType(Icons.Default.Garage, "Garagem"),
    RoomType(Icons.Default.Yard, "Quintal"),
    RoomType(Icons.Default.House, "Area de lazer"),
)
