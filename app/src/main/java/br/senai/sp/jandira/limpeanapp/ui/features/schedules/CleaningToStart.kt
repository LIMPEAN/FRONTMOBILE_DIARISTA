package br.senai.sp.jandira.limpeanapp.ui.features.schedules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.ui.components.HomeSection
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.ScheduleCleaning
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
//@Composable
//fun SchedulesScreen(
//    viewModel: SchedulesViewModel = SchedulesViewModel(FakeCleaningRepository())
//) {
//    val scaffoldState = rememberBottomSheetScaffoldState()
//    val scope = rememberCoroutineScope()
//
//    val cleanings = viewModel.cleanings
//
//
//    BottomSheetScaffold(
//        scaffoldState = scaffoldState,
//        sheetContent = {
//            CleaningDetails(fakeCleaningDetail)
//        },
//        sheetPeekHeight = 0.dp
//    ) {
//        CleaningContent(
//            cleanings = cleanings.map { it.toCleaningCardState() }
//        ){
//            scope.launch {
//                scaffoldState.bottomSheetState.expand()
//            }
//        }
//    }
//}





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

@Composable
fun YourCleanings(
    cleanings : List<Cleaning>,
    onCleaningDetail: (Cleaning) -> Unit,
    onCancel : (Cleaning) -> Unit,
    onStart : (Cleaning) -> Unit
) {
    HomeSection(title = "Suas faxinas") {
        ScheduleCleaning(
            cleanings = cleanings,
            onCleaningDetail = {
                onCleaningDetail(it)
            },
            onStart = { onStart(it) },
            onCancel = { onCancel(it) }
        )
    }
}
