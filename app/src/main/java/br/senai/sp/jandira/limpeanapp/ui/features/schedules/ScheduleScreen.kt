package br.senai.sp.jandira.limpeanapp.ui.features.schedules

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.ui.components.HomeContent
import br.senai.sp.jandira.limpeanapp.ui.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.ui.components.HomeSection
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.ModalCleaningDetails
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.md_theme_light_tertiary
import java.time.LocalTime

@Preview(showSystemUi = true)
@Composable
fun ScheduleScreen(
    nameUser : String = "Felipe",
    cleanings : List<Cleaning> = fakeCleanings,
    onAcceptPress : (Cleaning) -> Unit = {},
){
    var cleaning by remember {
        mutableStateOf(Cleaning())
    }
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    HomeLayout(
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                titleSmall = "Fique de olho!",
                titleLarge = "Agendamentos"
            )

        }
    ) { paddingValues ->

        HomeContent(paddingValues) {
            if (cleanings.isEmpty()) {
                CleaningNotFound()
            } else {
                SchedulesContent(
                    cleanings = cleanings,
                    onCleaningDetail = {
                        cleaning = it
                        isShowBottomSheet = true
                    },
                    onAcceptClick = {onAcceptPress(cleaning)},
                    onInfoClick = {
                        cleaning = it
                        isShowBottomSheet = true
                    }
                )
            }


        }
        if(isShowBottomSheet){
            ModalCleaningDetails(
                cleaningDetails = cleaning.toDetailsState(),
                onDismissRequest = { isShowBottomSheet = false},
                onAcceptPress = { onAcceptPress(cleaning) },
                onBackPress = { isShowBottomSheet = false}
            )
        }
    }
}

@Composable
fun SchedulesContent(
    cleanings : List<Cleaning>,
    onCleaningDetail: (Cleaning) -> Unit,
    onAcceptClick : (Cleaning) -> Unit,
    onInfoClick : (Cleaning) -> Unit
) {
    HomeSection(
        title = "Próximos Serviços",
    ) {
        ScheduleList(
            cleanings = cleanings,
            onStartClick = { onAcceptClick(it) },
            onInfoClick = { onInfoClick(it) },
            onCleaningDetail = { onCleaningDetail(it)}
        )
    }
}
@Preview
@Composable
fun ScheduleList(
    modifier : Modifier = Modifier,
    cleanings : List<Cleaning> = fakeCleanings,
    onCleaningDetail : (Cleaning) -> Unit = {},
    onStartClick: (Cleaning) -> Unit ={},
    onInfoClick: (Cleaning) -> Unit ={}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(cleanings){cleaning ->
            val model = cleaning.toCleaningCardState()
            CleaningCard(
                nameClient = model.nameClient,
                servicePrice = model.servicePrice,
                local = model.local,
                actions = {
                    val startedHour = cleaning.dateTime.toLocalTime()
                    val currentHour = LocalTime.now()
                    val readyToStart = currentHour >= startedHour
                    if(readyToStart){
                        ScheduleCardActions(
                            onStartClick = { onStartClick(cleaning)},
                            onInfoClick = { onInfoClick(cleaning)}
                        )
                    } else {
                        SeeDetailsButton() {
                            onInfoClick(cleaning)
                        }
                    }

                },
                onCleaningDetail = { onCleaningDetail(cleaning) }
            )
        }
    }
}
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Modo noturno")
@Composable
fun ScheduleCardActions(
    cleaning : Cleaning = Cleaning(),
    onStartClick: (Cleaning) -> Unit ={},
    onInfoClick : (Cleaning) -> Unit ={}
){
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
                onStartClick(cleaning)
            }) {
            Text(
                text = "Iniciar",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = poopins,
                color = MaterialTheme.colorScheme.onError
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        Button(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp , md_theme_light_tertiary),
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
                fontFamily = poopins,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

}

@Composable
fun SeeDetailsButton(
    onInfoClick : () -> Unit
) {
    Button(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp , md_theme_light_tertiary),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = md_theme_light_tertiary
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onInfoClick()
        }) {
        Text(
            text = "Ver detalhes",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = poopins
        )
    }
}
@Composable
fun CleaningNotFound() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.cleaning_null),
                contentDescription = "Luvas de trabalho."
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Nenhuma faxina encontrada.",
                style= MaterialTheme.typography.bodyLarge,
                fontFamily = poopins,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Procure por novas faxinas!",
                style= MaterialTheme.typography.bodyMedium,
                fontFamily = poopins,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

    }
}



