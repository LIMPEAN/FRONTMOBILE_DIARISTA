package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components



import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeAddressCleaning
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeQuantityRooms
import br.senai.sp.jandira.limpeanapp.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.greenColor
import com.example.compose.md_theme_light_onPrimaryContainer
import com.example.compose.md_theme_light_onTertiary
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondary
import com.example.compose.md_theme_light_tertiary
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class CleaningCardState(
    val id: Number,
    val nameClient: String,
    val servicePrice: Double,
    val local: String,
    val quantityRooms: List<RoomQuantity>
)


@Preview
@Composable
fun CleaningCard(
    modifier: Modifier = Modifier,
    nameClient: String =  "Felipe",
    servicePrice: Double = 10.0,
    dateTime : LocalDateTime? = LocalDateTime.now(),
    local: String = fakeAddressCleaning.street,
    quantityRooms: List<RoomQuantity>? = null,
    actions : @Composable () -> Unit ={},
    onCleaningDetail: ()->Unit = {},
    showRooms : Boolean = true
) {
    val customFontFamily = poopins
    val spacerModifier = Modifier.height(8.dp)
    Card(
        modifier = modifier
            .clickable {
                onCleaningDetail()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Image(
                modifier = modifier
                    .heightIn(min = 20.dp, max = 100.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.map_example),
                contentDescription = "Image map",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = spacerModifier)
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nameClient,
                    fontSize = 20.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "R\$ $servicePrice",
                    fontSize = 20.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
            ) {
                Text(
                    text = local,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
            quantityRooms?.let {
                QuantityRoomsInfo(quantityRooms = quantityRooms)
            }
            dateTime?.let {
                DateTimeInfo(dateTime = it)
            }
            Spacer(spacerModifier)
            actions()
        }
    }
}
enum class Meses(val nome: String) {
    JANEIRO("Janeiro"),
    FEVEREIRO("Fevereiro"),
    MARCO("Março"),
    ABRIL("Abril"),
    MAIO("Maio"),
    JUNHO("Junho"),
    JULHO("Julho"),
    AGOSTO("Agosto"),
    SETEMBRO("Setembro"),
    OUTUBRO("Outubro"),
    NOVEMBRO("Novembro"),
    DEZEMBRO("Dezembro")
}
fun obterNomeDoMes(numeroDoMes: Int): String? {
    return Meses.values().find { it.ordinal + 1 == numeroDoMes }?.nome
}
@Composable
fun ServiceScheduledActions(
    dateTime : LocalDateTime,
    onStartService : () -> Unit,
) {
    val nowMinutes = LocalDateTime.now().minute
    val started = dateTime.minute >= nowMinutes
    if(started){
        Button(
            onClick = {onStartService()}
        ) {
            Text(text = "Iniciar faxina")
        }
    }
}
fun formatarParaHora(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm'h'")
    return localDateTime.format(formatter)
}
@Composable
fun DateTimeInfo(
    dateTime: LocalDateTime
) {
    Column {
        Text(
            text = "Marcado para dia ${dateTime.dayOfMonth} de ${obterNomeDoMes(dateTime.monthValue)}" ,
            fontFamily = poopins,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = formatarParaHora(dateTime) ,
            fontFamily = poopins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyMedium,
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

sealed class FindCleaningCardEvent {
    data class onInfoClick(val cleaning: Cleaning) : FindCleaningCardEvent()
    data class onAcceptClick(val cleaning: Cleaning) : FindCleaningCardEvent()
}


@Preview(showBackground = true)
@Composable
fun FindCleaningCardActions(
    cleaning : Cleaning = Cleaning(),
    onAcceptClick: (Cleaning) -> Unit ={},
    onInfoClick : (Cleaning) -> Unit ={}
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.fillMaxWidth(0.48f),
            onClick = {
           onAcceptClick(cleaning)
        }) {
            Text(
                text = "Aceitar",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        Button(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp , MaterialTheme.colorScheme.tertiary),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            onInfoClick(cleaning)
        }) {
            Text(
                text = "Ver detalhes",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }

}
@Composable
private fun QuantityRoomsInfo(
    modifier : Modifier = Modifier,
    quantityRooms : List<RoomQuantity>
){
    Row(
        modifier = Modifier.fillMaxWidth( 1f),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        quantityRooms.forEach {room ->
            if(room.quantity != null){
                Row() {
                    Icon(
                        imageVector = room.roomType.icon,
                        contentDescription = room.roomType.name,
                        tint = Color(0xFF393939)
                    )
                    Text(text = room.quantity.toString(),
                        color = Color(0xFF393939))
                }
            }
        }
    }
}

@Preview
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CleaningCardPreview() {
    LimpeanAppTheme {
        CleaningCard()
    }
}
