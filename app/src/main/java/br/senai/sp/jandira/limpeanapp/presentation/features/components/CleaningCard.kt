package br.senai.sp.jandira.limpeanapp.presentation.features.components



import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GoogleMapState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data.fakeAddressCleaning
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data.fakeQuantityRooms
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class CleaningCardState(
    val id: Number,
    val nameClient: String,
    val servicePrice: Double,
    val local: String,
    val quantityRooms: List<RoomQuantity>
)



@Composable
fun CleaningCard(
    modifier: Modifier = Modifier,
    mapContainer : @Composable () -> Unit = { Text(text = "Oi")},
    nameClient: String =  "Felipe",
    servicePrice: Double = 10.0,
    dateTime : LocalDateTime? = LocalDateTime.now(),
    local: String = fakeAddressCleaning.street,
    quantityRooms: List<RoomQuantity>? = null,
    actions : @Composable () -> Unit ={},
    onCleaningDetail: ()->Unit = {},
) {
    val customFontFamily = poopins
    val spacerModifier = Modifier.height(8.dp)
    Card(
        modifier = modifier
            .clickable {
                onCleaningDetail()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {

            mapContainer()
            Spacer(modifier = spacerModifier)
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nameClient,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "R\$ $servicePrice",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
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
                    style = MaterialTheme.typography.bodySmall
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
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = formatarParaHora(dateTime) ,
            fontFamily = poopins,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
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
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
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


@Composable
fun StartedCleaningActions(
    cleaning : Cleaning,
    onFinished : (Cleaning) -> Unit,
    onInfoClick: (Cleaning) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth(0.48f),
            onClick = {
                onFinished(cleaning)
            }) {
            Text(
                text = "Finalizar",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onError
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        Button(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp , MaterialTheme.colorScheme.tertiary),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onInfoClick(cleaning)
            }) {
            Text(
                text = "Ver detalhes",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Preview
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun StartedPreview() {
    LimpeanAppTheme {
        Card {
            StartedCleaningActions(cleaning = Cleaning(), onFinished = {}, onInfoClick ={} )
        }
    }
}
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
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            onInfoClick(cleaning)
        }) {
            Text(
                text = "Ver detalhes",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ActionsPreview() {
    LimpeanAppTheme {
        FindCleaningCardActions()
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
            if(room.quantity != null && room.quantity.toInt() > 0){
                Row() {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = room.roomType.icon,
                        contentDescription = room.roomType.name,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(text = room.quantity.toString(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
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
        var googleMapState by remember {
            mutableStateOf(GoogleMapState(
                local = LatLng(
                    -23.595515,
                    -46.90905300000001
                )
            ))
        }
        CleaningCard(
            quantityRooms = fakeQuantityRooms,
            mapContainer = {
                if(googleMapState.isLoading){
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
                googleMapState.local?.let {
                    GoogleMapContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        local = it,
                        name = googleMapState.name,
                        place = googleMapState.place
                    )
                }
            }
        )
    }
}


@Composable
fun ImageMap() {
    Image(
        modifier = Modifier
            .heightIn(min = 20.dp, max = 100.dp)
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.map_example),
        contentDescription = "Image map",
        contentScale = ContentScale.Crop
    )
}