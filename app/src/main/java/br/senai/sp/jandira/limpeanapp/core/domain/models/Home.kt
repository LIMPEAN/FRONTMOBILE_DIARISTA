package br.senai.sp.jandira.limpeanapp.core.domain.models

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
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.QuantityRoomsCategory


data class Home(
    val rooms : List<RoomQuantity>,
    val name : String,
    val homeAddress : HomeAddress
)
data class HomeAddress(
    val cep: String = "",
    val street: String = "",
    val district: String = "",
    val city: String = "",
    val state: String = "",
    val number: String = "",
    val complement: String? = null,
    val publicPlace: String = "",
    val numberHouse : String = ""
) {
}

data class RoomQuantity(
    val roomType : RoomType,
    val quantity: Number?
)
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
fun RoomQuantity.inCleaningCard(): QuantityRoomsCategory {
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