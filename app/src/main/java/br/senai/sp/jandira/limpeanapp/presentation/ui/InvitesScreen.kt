package br.senai.sp.jandira.limpeanapp.presentation.ui

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeSection
import br.senai.sp.jandira.limpeanapp.presentation.features.components.formatarParaHora
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme


@Preview
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvitesScreenPreview() {
    LimpeanAppTheme {
        InvitesScreen()
    }
}
@Composable
fun InvitesScreen() {
    var isShowBottomSheet by remember {
        mutableStateOf(true)
    }
    var selectedCleaning by remember {
        mutableStateOf<Cleaning>(Cleaning())
    }
    HomeSection(title = "Convites") {
        InvitesList(
            onSeeInvite = {
                selectedCleaning = it
                isShowBottomSheet = true
            }
        )
    }

    if(isShowBottomSheet){
        SeeInvite(
            onCancel = { isShowBottomSheet = false},
            selectedCleaning = selectedCleaning
        )

    }
fakeCleanings
}
fun formatDoubleToString(value: Double): String {
    return String.format("%.0f", value)
}
@Preview
@Composable
fun SeeInvitePreview() {
    LimpeanAppTheme {
        SeeInvite(onCancel = {}, selectedCleaning = fakeCleanings[0])
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeInvite(
    onCancel : () -> Unit,
    selectedCleaning : Cleaning = Cleaning()
) {
    var price by remember {
        mutableDoubleStateOf(0.0)
    }
    ModalBottomSheet(
        modifier = Modifier.padding(bottom = 40.dp),
        onDismissRequest = onCancel
    ) {
        CleaningDetails(state = selectedCleaning.toDetailsState()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                SliderPrice(price = price, onPriceChange = {price = it})
                OutlinedTextField(value = price.toInt().toString(), onValueChange = {price = it.toDouble()})
                Text(text = "R$ ${formatDoubleToString(price)}")

                Button(onClick = {

                }) {
                    Text(text = "Enviar Preço")
                }
            }
        }
    }
}

@Preview
@Composable
fun SliderTest() {
    LimpeanAppTheme {
        SliderPrice(0.0,{})
    }
}
@Composable
fun SliderPrice(
    price : Double,
    onPriceChange : (Double) -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Você pode observar as alterações de valor usando o lambda onValueChange
        Slider(
            value = price.toFloat(),
            onValueChange = {
                // Manipule a mudança de valor aqui
                onPriceChange(it.toDouble())
                println("Slider value changed to $it")
            },
            valueRange = 0.0f..1000.0f,
            steps = 1200
        )
    }
}
@Composable
fun InvitesList(
    onSeeInvite: (Cleaning) -> Unit
) {
    LazyColumn(){
        items(fakeCleanings){service ->
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSeeInvite(service)
                    },
                headlineContent = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = "#${service.id}",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = service.client.name,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    if(service.price > 0.0){
                        Text(
                            text = "R$ ${service.price }",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                },
                supportingContent = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        service.details.observations?.let {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = it,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                },
                trailingContent = {
                    Log.i("TEST", service.toString())
                    var price = InvitesStatus.PENDENTE_DE_PRECO

                    price = if(service.price == 0.0){
                        InvitesStatus.PENDENTE_DE_PRECO
                    } else {
                        InvitesStatus.PRECO_ENVIADO
                    }
                    val statusColor = when(price){
                        InvitesStatus.PENDENTE_DE_PRECO -> {
                            MaterialTheme.colorScheme.primary
                        }
                        InvitesStatus.PRECO_ENVIADO -> {
                            MaterialTheme.colorScheme.tertiary
                        }
                    }
                    Column() {

                        val date = service.dateTime
                        Text(
                            text = price.nomeLegivel,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = Poppins,
                            color = statusColor
                        )
                        Text(
                            text = "${date.dayOfMonth}/${date.monthValue}/${date.year } ás ${formatarParaHora(service.dateTime)}".uppercase(),
                            color = statusColor,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = Poppins,
                        )
                    }
                }
            )
            Divider()
        }
    }
}
enum class InvitesStatus(val id : Number, val nomeLegivel : String) {
    PENDENTE_DE_PRECO(1, "Pendente de Preço"), PRECO_ENVIADO(2, "Aguardando Aprovação")
}