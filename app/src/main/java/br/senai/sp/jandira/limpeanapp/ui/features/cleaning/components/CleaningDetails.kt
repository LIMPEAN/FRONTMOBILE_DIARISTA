package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Question
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeHomeInfo
import br.senai.sp.jandira.limpeanapp.ui.components.HomeSection
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeCleaningDetail
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeCleaningSupport
import br.senai.sp.jandira.limpeanapp.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary


data class CleaningDetailsState(
    val primordialInfo: PrimordialInfoState = PrimordialInfoState(),
    val addressCleaning : AddressCleaningState = AddressCleaningState(),
    val aboutClientInfo: AboutClientState = AboutClientState(),
    val cleaningSupport : CleaningSupportState = CleaningSupportState()
)

data class CleaningSupportState(
    val questions : List<Question> = emptyList(),
    val typeCleaning : TypeCleaningEnum = TypeCleaningEnum.PADRAO,
    val rooms : List<RoomQuantity> = emptyList()
)

@Composable
fun CleaningDetails(
    state: CleaningDetailsState,
    onAcceptPress: () -> Unit,
    onBackPress: () -> Unit
) {
    val modifier = Modifier.fillMaxWidth()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainServiceInformation(state.primordialInfo)
        Divider(modifier)
        AddressCleaningInfo(state.addressCleaning)
        Divider(modifier)
        AboutClient(state.aboutClientInfo)
        Divider(modifier)
        CleaningSupport(state.cleaningSupport)
        Divider(modifier)
        DoYouLikeService(
            onAcceptPress = onAcceptPress,
            onBackPress = onBackPress,
        )
    }

}
data class PrimordialInfoState(
    val price : Double = 0.0,
    val date : String = "",
    val startTime : String = ""
)

@Composable
fun MainServiceInformation(
    state: PrimordialInfoState
) {
    HomeSection(
        title = "Dados do Serviço"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)

        ) {
            ItemSection(name = "Valor definido", value = "${state.price}")
            ItemSection(name = "Data da Faxina", value = state.date)
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            ItemSection(name = "Hora de Início", value = state.startTime + "h")
            ItemSection(name = "Duração estimada", value = "-")

        }


    }
}


data class AddressCleaningState (
    val street : String = "",
    val district : String = "",
    val city : String = "" ,
    val complement : String? = null,
    val state : String = ""
)

@Composable
fun AddressCleaningInfo(
    state : AddressCleaningState
) {
    HomeSection(title = "Endereço") {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            ItemSection(name = "Logradouro",value = state.street)
            ItemSection(name = "Bairro", value = state.district)
            ItemSection(name = "Cidade", value = state.city)
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            ItemSection(name = "Complemento", value = state.complement?: "Ausente.")
            ItemSection(name = "Estado", value = state.state)
        }
    }
}


data class HomeInfoState(
    val typeHouse : String = "",
    val name:  String = ""
)
data class AboutClientState(
    val clientInfo : ClientInfoState = ClientInfoState(),
    val homeInfo : HomeInfoState = HomeInfoState()
)

@Composable
fun AboutClient(
    state : AboutClientState
) {
    HomeSection(
        title = "Sobre o Cliente"
    ){
        ClientInfo(state.clientInfo)
        HomeClientInfo(state.homeInfo)

    }
}

@Composable
fun DoYouLikeService(
    onBackPress : () -> Unit = {},
    onAcceptPress : () -> Unit = {}
) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    if(showDialog){
       ConfirmDialog(
           onDismissRequest = { showDialog = false },
           onConfirmPress = {
               onAcceptPress()
           },
           onCancelPress = {
               onBackPress()
           },
       )
    }

    HomeSection(
        title = "Gostou do Serviço?"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary
                ),
                onClick = { showDialog = true }
            ) {
                Text(
                    text = "Sim, eu quero!",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = poopins
                )
            }
        }

    }
}

@Composable
fun ConfirmDialog(
    onDismissRequest : () -> Unit,
    onConfirmPress :() -> Unit,
    onCancelPress : () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = "Confirmação",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = poopins
            )
        },
        text = {
            Text(
                text = "Está certo disso?",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = poopins
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = onConfirmPress) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(onClick = onCancelPress) {
                Text(text = "Cancelar")
            }
        },
    )
}
@Composable
fun ItemSection(name : String, value : String){
    ItemSection(text = name){
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontFamily = Poppins
        )
    }
}
@Composable
fun ItemSection(
    text : String,
    content: @Composable ()-> Unit
) {
    Column() {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontFamily = Poppins
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
        Spacer(modifier = Modifier.height(12.dp))
    }
}

data class ClientInfoState(
    val name : String = "",
    val assentment: Number = 0.0
)

@Composable
fun ClientInfo(
    state : ClientInfoState
) {
    Card(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.man),
                contentDescription = null
            )
            Column(
                Modifier.padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = poopins,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = null,
                        modifier = Modifier.size(21.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${state.assentment}",
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = poopins,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }

        }
    }
}




@Composable
fun CleaningSupport(
   state : CleaningSupportState
){
    val modifier = Modifier.fillMaxWidth()
    val spaceModifier = Modifier.height(12.dp)
    HomeSection(title = "Suporte a faxina") {
        QuestionsSection(state.questions)
        Spacer(spaceModifier)
        TypeOfCleningSection(state.typeCleaning)
        Spacer(spaceModifier)
        RoomsToBeCleaned(modifier, state.rooms)
    }
}
data class RoomsQuantityItem(
    val icon : ImageVector,
    val name : String,
    val quantity : Number?
)
@Composable
fun RoomsToBeCleaned(
    modifier: Modifier,
    rooms: List<RoomQuantity>
) {
    CleaningFormSection(
        modifier = Modifier.fillMaxWidth(),
        title = "Quais cômodos devem ser limpos?"
    ) {
        Divider(modifier)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            rooms.map {
                RoomsQuantity(
                    icon = it.roomType.icon,
                    name = it.roomType.name,
                    quantity = it.quantity
                )
            }

        }
    }
}

@Composable
fun CleaningFormSection(
    modifier : Modifier = Modifier,
    horizontalAlignment : Alignment.Horizontal = Alignment.Start,
    title : String,
    content: @Composable () -> Unit
){
    Column(
        modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = poopins,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        content()
    }
}

@Composable
fun QuestionsSection(
    questions :  List<Question>
) {
    CleaningFormSection(title = "Perguntas") {
        questions.map {
            CheckQuestion(
                question = it.question,
                checked = it.answer,
            )
        }
    }
}

@Composable
fun CheckQuestion(
    question: String,
    checked : Boolean = false,
    enabled : Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked =  checked,
            onCheckedChange = { TODO("READ ONLY") },
            enabled = enabled
        )
        Text(
            text = question,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = poopins,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun TypeOfCleningSection(
    typeCleaning: TypeCleaningEnum
) {
    CleaningFormSection(
        modifier = Modifier.fillMaxWidth(),
        title = "Tipo de Faxina"
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = true,
                onClick = { TODO("READ ONLY") },
                enabled = false
            )
            Text(
                text = typeCleaning.name,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poopins,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HomeClientInfo(
    state : HomeInfoState = fakeHomeInfo
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
            Column(
                Modifier.padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = state.typeHouse,
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = poopins,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = poopins,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

        }
    }
}


@Composable
fun RoomsQuantity(
    icon : ImageVector,
    name: String,
    quantity : Number?,
) {
    if (quantity != null){
        ListItem(
            leadingContent = {
                Icon(imageVector = icon, contentDescription = name)
            },
            headlineContent = {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = poopins,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                Text(text = "$quantity")
            },
            )
    }

}


@Composable
fun CleaningDetailsPreview() {
    LimpeanAppTheme {
        CleaningDetails(state = fakeCleaningDetail, onAcceptPress = { /*TODO*/ }) {
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CleaningInfoPreview() {
    LimpeanAppTheme {
        CleaningSupport(state = fakeCleaningSupport)
    }
    
}