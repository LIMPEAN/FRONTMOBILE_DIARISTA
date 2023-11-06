package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components


import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Question
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.fakeHomeInfo
import br.senai.sp.jandira.limpeanapp.ui.components.HomeSection
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins




data class CleaningDetailsState(
    val primordialInfo: PrimordialInfoState = PrimordialInfoState(),
    val addressCleaning : AddressCleaningState = AddressCleaningState(),
    val aboutClientInfo: AboutClientState = AboutClientState(),
    val cleaningSupport : CleaningSupportState = CleaningSupportState()
)

data class CleaningSupportState(
    val questions : List<Question> = emptyList(),
    val typeCleaning : TypeCleaning = TypeCleaning.DEFAULT,
    val rooms : List<RoomQuantity> = emptyList()
)

@Composable
fun CleaningDetails(
    state: CleaningDetailsState,
    onBackPress: () -> Unit
) {
    val modifier = Modifier.fillMaxWidth()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            onBackPress = onBackPress
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
            SubSection(text = "Valor definido") {
                Text(text = "${state.price}")
            }
            SubSection(text = "Data da Faxina") {
                Text(text = state.date)
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            SubSection(text = "Hora de Início"){
                Text(text = state.startTime + "h")
            }
            SubSection(text = "Duração estimada"){
                Text(text = "18:00h")
            }
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
            SubSection(text = "Logradouro"){
                Text(text = state.street)
            }
            SubSection(text = "Bairro"){
                Text(text = state.district)
            }
            SubSection(text = "Cidade"){
                Text(text = state.city)
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            SubSection(text = "Complemento"){
                Text(text = state.complement?: "Ausente.")
            }
            SubSection(text = "Estado"){
                Text(text = state.state)
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoYouLikeService(
    onBackPress : () -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    if(showDialog){
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
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false}) {
                    Text(text = "Cancelar")
                }
            },
        )
    }

    HomeSection(
        title = "Gostou do Serviço?"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(onClick = { showDialog = true }) {
                Text(text = "Sim, eu quero!")
            }
            Button(onClick = { onBackPress() }) {
                Text(text = "Não gostei :( Voltar")
            }
        }

    }
}
@Composable
fun SubSection(
    text : String,
    content: @Composable ()-> Unit
) {
    Column() {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
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
        shape = RoundedCornerShape(12.dp)
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
                    fontFamily = poopins
                )
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = null,
                        modifier = Modifier.size(21.dp)
                    )
                    Text(
                        text = "${state.assentment}",
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = poopins,
                        textAlign = TextAlign.Center
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
            fontWeight = FontWeight.Normal
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
            fontFamily = poopins
        )
    }
}

@Composable
fun TypeOfCleningSection(
    typeCleaning: TypeCleaning
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
                text = typeCleaning.inPortuguese,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poopins
            )
        }
    }
}

@Composable
fun HomeClientInfo(
    state : HomeInfoState = fakeHomeInfo
) {
    Card(
        modifier = Modifier.padding(12.dp),
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
                    fontFamily = poopins
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = poopins
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
                    fontFamily = poopins
                )
            },
            trailingContent = {
                Text(text = "$quantity")
            },
            )
    }

}
