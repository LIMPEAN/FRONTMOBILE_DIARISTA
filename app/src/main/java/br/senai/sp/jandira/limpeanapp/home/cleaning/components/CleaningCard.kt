package br.senai.sp.jandira.limpeanapp.home.cleaning.components



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Shower

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun CleaningCard(
    nameClient: String,
    servicePrice: Double,
    local: String,
    quantityRooms: List<QuantityRoomsCategory>,
    actions : @Composable () -> Unit,
    onCleaningDetail: ()->Unit
) {
    val customFontFamily = poopins
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCleaningDetail()
            },
        colors = CardDefaults.cardColors(
            Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                painter = painterResource(id = R.drawable.map_example),
                contentDescription = "Image map",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nameClient,
                    fontSize = 20.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF393939),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "R\$ $servicePrice",
                    fontSize = 20.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3147F5)
                )
            }
            Text(
                text = local,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
            QuantityRoomsInfo(quantityRooms = quantityRooms)
            Spacer(modifier = Modifier.height(10.dp))
            actions()
        }
    }
}

@Preview
@Composable
fun CardPrev() {
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
    LimpeanAppTheme {
        CleaningCard(
            nameClient = "Maria Dolores",
            servicePrice = 400.00,
            local = "06600-025, Osasco,SP",
            quantityRooms = quantityRooms,
            actions = {
                CleaningCardActions(
                    onStart = {},
                    onCancel = {}
                )
            },
            onCleaningDetail = {}
        )
    }
}
@Composable
fun CleaningCardActions(
    onStart: () -> Unit,
    onCancel : () ->Unit
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.46f),
            shape = RoundedCornerShape(size = 12.dp),
            colors = ButtonDefaults.buttonColors(md_theme_light_primary),
            onClick = { onStart() }
        ) {
            Text(
                text = "Iniciar",
                fontFamily = poopins,
                fontWeight = FontWeight.Bold,
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(size = 12.dp),
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red
            ),
            onClick = { onCancel() }
        ) {
            Text(
                text = "Cancelar",
                fontFamily = poopins,
                fontWeight = FontWeight.Normal
            )

        }
    }
}

data class QuantityRoomsCategory(
    val name: String,
    val icon : ImageVector,
    val quantity: Number?
)

@Composable
private fun QuantityRoomsInfo(
    quantityRooms : List<QuantityRoomsCategory>
){
    Row(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        quantityRooms.forEach {room ->
            if(room.quantity != null){
                Row {
                    Icon(
                        imageVector = room.icon,
                        contentDescription = room.name,
                        tint = Color(0xFF393939)
                    )
                    Text(text = room.quantity.toString(),
                        color = Color(0xFF393939))
                }
            }


        }

    }
}
