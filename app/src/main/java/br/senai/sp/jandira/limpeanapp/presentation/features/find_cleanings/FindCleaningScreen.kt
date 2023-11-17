package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeContent
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeSection
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.schedule.CleaningNotFound
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.FindCleaningCardActions
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindCleaningScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel : FindCleaningViewModel = hiltViewModel()
) {
    val cleanings = viewModel.cleanings.collectAsState(initial = emptyList())

    val nameUser = viewModel.emailUser

    val snackbarHostState = remember { SnackbarHostState() }



//    LaunchedEffect(key1 = true) {
//        viewModel.uiEvent.collect { event ->
//            when(event) {
//                is UiEvent.ShowSnackbar -> {
//                    val result = snackbarHostState.showSnackbar(
//                        message = event.message,
//                        actionLabel = event.action
//                    )
////                    actionif(result == SnackbarResult.ActionPerformed) {
////                        viewModel.onEvent(FindCleaningEvent.OnCleaningInfoClick(Cle))
////                    }
//
//                }
//                is UiEvent.ShowBottomSheet -> {
//
//                }
//                is UiEvent.Navigate -> onNavigate(event)
//                else -> Unit
//            }
//        }
//    }

    FindCleaningContent(
        nameUser = nameUser,
        cleanings = cleanings.value,
        onAcceptPress = {
            viewModel.onEvent(br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningEvent.OnAcceptClick(it))
        }
    )
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkMode")
@Composable
fun FindCleaningPreview() {
    LimpeanAppTheme {
        FindCleaningContent()
    }
}
@Composable
fun FindCleaningContent(
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
                modifier = Modifier.padding(horizontal = 24.dp),
                titleSmall = "Bem vindo (a)",
                titleLarge = nameUser
            )
        }
    ) { paddingValues ->

        HomeContent(paddingValues) {
            if (cleanings.isEmpty()) {
                CleaningNotFound()
            } else {
                FindYourCleanings(
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
fun FindYourCleanings(
    cleanings : List<Cleaning>,
    onCleaningDetail: (Cleaning) -> Unit,
    onAcceptClick : (Cleaning) -> Unit,
    onInfoClick : (Cleaning) -> Unit
) {
    HomeSection(
        title = "Encontre seus serviços",
    ) {
        LazyColumn(){
            item {
                SearchBar()
            }
            items(cleanings) {cleaning ->
                val model = cleaning.toCleaningCardState()
                CleaningCard(
                    nameClient = model.nameClient,
                    servicePrice = model.servicePrice,
                    local = model.local,
                    quantityRooms = model.quantityRooms,
                    actions = {
                        FindCleaningCardActions(
                            onAcceptClick = { onAcceptClick(cleaning)},
                            onInfoClick = { onInfoClick(cleaning)}
                        )
                    },
                    onCleaningDetail = { onCleaningDetail(cleaning) }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SearchBar() {

        OutlinedTextField(
            value = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), // Peso do TextField
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "icone de pesquisa"
                )
            },
            shape = RoundedCornerShape(12.dp)
        )

}

@Preview
@Composable
fun FindCleaningList(
    modifier : Modifier = Modifier,
    cleanings : List<Cleaning> = fakeCleanings,
    onCleaningDetail : (Cleaning) -> Unit = {},
    onAcceptClick: (Cleaning) -> Unit ={},
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
                quantityRooms = model.quantityRooms,
                actions = {
                    FindCleaningCardActions(
                        onAcceptClick = { onAcceptClick(cleaning)},
                        onInfoClick = { onInfoClick(cleaning)}
                    )
                },
                onCleaningDetail = { onCleaningDetail(cleaning) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalCleaningDetails(
    cleaningDetails : CleaningDetailsState,
    onDismissRequest : () -> Unit,
    onAcceptPress : () -> Unit,
    onBackPress : () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        CleaningDetails(
            state = cleaningDetails,
            onAcceptPress = onAcceptPress,
            onBackPress = onBackPress
        )
    }
}

