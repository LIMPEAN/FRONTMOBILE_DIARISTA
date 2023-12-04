package br.senai.sp.jandira.limpeanapp.presentation.features.invites

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.LoadingDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.HeaderText
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Preview
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvitesScreenPreview() {
    val longlist = List(5) { fakeCleanings }.flatten()

    LimpeanAppTheme {
        val context = LocalContext.current
        InvitesScreenContent(longlist)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitesScreen(
    viewModel: InvitesViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect( state.message){
        if(state.message.isNotBlank()){
            Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
        }
    }


    InvitesScreen(
        invites = state.cleanings,
        onSendPrice = {
            viewModel.sendPrice(
                id = it.idCleaning,
                price = it.price
            )
        },
        isLoading = state.isLoading
    )

}
@Composable
private fun InvitesScreen(
    isLoading: Boolean  = false,
    invites : List<Cleaning>,
    onSendPrice: (SendPriceRequest) -> Unit
){
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    var selectedCleaning by remember {
        mutableStateOf<Cleaning>(fakeCleanings[0])
    }
    if(invites.isEmpty()){
        YouDontHaveInvites()
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            title = "Convites",
            style = MaterialTheme.typography.titleMedium
        )
        if(isLoading){
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        InvitesList(
            invites = invites,
            onSeeInvite = {
                selectedCleaning = it
                isShowBottomSheet = true
            }
        )
    }

    if(isShowBottomSheet){
        SeeInvite(
            onCancel = { isShowBottomSheet = false},
            selectedCleaning = selectedCleaning,
            onSendPrice = {
                isShowBottomSheet = false
                onSendPrice(it)
            }
        )

    }

}
fun formatDoubleToString(value: Double): String {
    return String.format("%.2f", value)
}
@Preview
@Composable
fun SeeInvitePreview() {
    LimpeanAppTheme {
        SeeInvite(onCancel = {}, selectedCleaning = fakeCleanings[0], onSendPrice = {})
    }
}
data class SendPriceRequest(
    val idCleaning: Number,
    val price : Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun InvitesScreenContent(
    invites : List<Cleaning> = emptyList()
) {

}
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun YouDontHaveInvites() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = R.drawable.no_invites),
                contentDescription = "Não possui convites."
            )
            Text(
                text = "Você ainda não possui convites.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Poppins
            )
        }
    }
}

@Composable
fun CleaningDetailsSheetContent(
    onCancel: () -> Unit,
    onSendPrice: (SendPriceRequest) -> Unit,
    selectedCleaning : Cleaning = Cleaning()
) {
    var price by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        CleaningDetails(state = selectedCleaning.toDetailsState()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SliderPrice(price = price, onPriceChange = { price = it })
                OutlinedTextField(
                    value = price.toInt().toString(),
                    onValueChange = {
                        price = if (it == "") 0.0 else it.toDouble()
                    }
                )
                Text(text = "R$ ${formatDoubleToString(price)}")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSendPrice(
                            SendPriceRequest(
                                idCleaning = selectedCleaning.id!!,
                                price = price
                            )
                        )
                    }
                ) {
                    Text(text = "Enviar Preço")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeInvite(
    onCancel : () -> Unit,
    selectedCleaning : Cleaning = fakeCleanings[0],
    onSendPrice : (SendPriceRequest) -> Unit
) {
    var price by remember {
        mutableDoubleStateOf(0.0)
    }

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize(),
        onDismissRequest = onCancel,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .navigationBarsPadding()
        ) {
            CleaningDetails(state = selectedCleaning.toDetailsState()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    SliderPrice(price = price, onPriceChange = {price = it})
                    OutlinedTextField(value = price.toInt().toString(), onValueChange = {price = if(it == ""){0.0}else{it.toDouble()} })
                    Text(text = "R$ ${formatDoubleToString(price)}")

                    Spacer(modifier = Modifier.height(16.dp)) // Adicione um espaço para separar o conteúdo


                    Button(onClick = {
                        onSendPrice(
                            SendPriceRequest(
                                idCleaning = selectedCleaning.id!!,
                                price = price
                            )
                        )
                    }) {
                        Text(text = "Enviar Preço")
                    }
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
        Slider(
            value = price.toFloat(),
            onValueChange = {
                val roundedValue = it.roundToInt().toDouble()
                onPriceChange(roundedValue)
            },
            valueRange = 0.0f..1000.0f,
            steps = 1200
        )
    }
}
@Composable
fun InvitesList(
    modifier : Modifier = Modifier.fillMaxSize(),
    onSeeInvite: (Cleaning) -> Unit,
    invites : List<Cleaning> = emptyList()
) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = true
    ){
        items(invites){service ->
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
                    service.price?.let {
                        Text(
                            text = "R$ ${service.price}",
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
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {

                        val date = service.dateTime
                        Text(
                            text = price.nomeLegivel,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = Poppins,
                            color = statusColor
                        )
//                        Text(
//                            text = "${date.dayOfMonth}/${date.monthValue}/${date.year } ás ${formatarParaHora(service.dateTime)}".uppercase(),
//                            color = statusColor,
//                            style = MaterialTheme.typography.bodySmall,
//                            fontFamily = Poppins,
//                        )
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