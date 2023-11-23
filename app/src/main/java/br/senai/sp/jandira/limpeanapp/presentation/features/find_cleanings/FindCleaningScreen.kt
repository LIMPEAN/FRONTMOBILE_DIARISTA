package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.models.toAddressCleaningState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeContent
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeSection
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.ConfirmDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.DoYouLikeService
import br.senai.sp.jandira.limpeanapp.presentation.features.schedule.CleaningNotFound
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.FindCleaningCardActions
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.StartedCleaningActions
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindCleaningScreen(
    viewModel: FindCleaningViewModel = hiltViewModel()
) {


    val state = viewModel.state.value
    val diaristState = viewModel.getDiaristState.value
    val context = LocalContext.current
    val selectedCleaning = viewModel.selectedCleaning.value

    LaunchedEffect(state.message) {
        if (state.message.isNotBlank()) {
            Toast.makeText(
                context,
                state.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    FindCleaningContent(
        nameUser = diaristState.diarist.name,
        isLoadingDiarist = diaristState.isLoading,
        cleanings = state.openServices,
        onAcceptClick = {
            viewModel.onEvent(FindCleaningEvent.OnAcceptClick(it))
        },
        onCleaningDetail = {
            viewModel.onEvent(FindCleaningEvent.OnCleaningInfoClick(it))
        },
        isLoadingCleaning = state.isLoadingCleanings,
        onInfoClick = {
            viewModel.onEvent(FindCleaningEvent.OnCleaningInfoClick(it))
        },
        selectedCleaning = selectedCleaning,
    )
    if (state.isLoading) {
        LoadingDialog()
    }
}


@Preview
@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { /*TODO*/ }) {
        Box(contentAlignment = Alignment.Center) {
            Card(
                Modifier.size(200.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }
        }

    }
}

@Preview
@Composable
fun LoadingDialogPreview() {
    LimpeanAppTheme {
        LoadingDialog()
    }
}
data class Category(
    val name: String,
    val items: List<Cleaning> = emptyList()
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkMode")
@Composable
fun FindCleaningPreview() {
    LimpeanAppTheme {
        FindCleaningContent(
            nameUser = "TEste",
            cleanings = fakeCleanings,
            isLoadingCleaning = false,
            isLoadingDiarist = false,
            onInfoClick = {},
            onAcceptClick = {},
            onCleaningDetail = {},
            startedCleaning = listOf(
                Cleaning()
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FindCleaningContent(
    nameUser: String = "Felipe",
    startedCleaning: List<Cleaning> = emptyList(),
    cleanings: List<Cleaning> = fakeCleanings,
    onCleaningDetail: (Cleaning) -> Unit,
    onInfoClick: (Cleaning) -> Unit,
    onAcceptClick: (Cleaning) -> Unit,
    isLoadingCleaning: Boolean,
    isLoadingDiarist: Boolean,
    selectedCleaning: Cleaning = Cleaning()
) {
    var selectedCleaning by remember {
        mutableStateOf(Cleaning())
    }
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    var isShowAssentment by remember {
        mutableStateOf(false)
    }


    HomeLayout(
        topBar = {
            if (isLoadingDiarist) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                HomeTopBar(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    titleSmall = "Bem vindo (a)",
                    titleLarge = nameUser
                )
            }

        }
    ) { paddingValues ->

        HomeContent(paddingValues) {
            if (isLoadingCleaning) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    CircularProgressIndicator()
                }
            }
            if (cleanings.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CleaningNotFound()
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 18.dp),
                modifier = Modifier.fillMaxSize()
            ){
                if(startedCleaning.isNotEmpty()){
                    stickyHeader {
                        HomeSection(
                            modifier = Modifier.padding(vertical = 18.dp),
                            title = "Em andamento"
                        ){}
                    }
                    items(startedCleaning){cleaning ->
                        val address = cleaning.address
                        CleaningCard(
                            nameClient = cleaning.client.name,
                            servicePrice = cleaning.price,
                            dateTime = cleaning.dateTime,
                            local = "${address.street}, ${address.number} - ${address.city}, ${address.state}",
                            quantityRooms = cleaning.details.roomsQuantity,
                            onCleaningDetail = {
                                selectedCleaning = cleaning
                                isShowBottomSheet = true
                            },
                            actions = {
                                StartedCleaningActions(
                                    cleaning = cleaning,
                                    onFinished = {
                                         isShowDialog = true
                                    },
                                    onInfoClick = {}
                                )
                            }
                        )
                    }
                }
                if(cleanings.isNotEmpty()){
                    stickyHeader {
                        HomeSection(
                            modifier = Modifier.padding(vertical = 18.dp),
                            title = "Em andamento"
                        ){}
                    }
                    items(startedCleaning){cleaning ->
                        val address = cleaning.address
                        CleaningCard(
                            nameClient = cleaning.client.name,
                            servicePrice = cleaning.price,
                            dateTime = cleaning.dateTime,
                            local = "${address.street}, ${address.number} - ${address.city}, ${address.state}",
                            quantityRooms = cleaning.details.roomsQuantity,
                            onCleaningDetail = {
                                selectedCleaning = cleaning
                                isShowBottomSheet = true
                            },
                            actions = {
                                FindCleaningCardActions(
                                    cleaning = cleaning,
                                    onAcceptClick = { onAcceptClick(cleaning) },
                                    onInfoClick = {
                                        selectedCleaning = cleaning
                                        isShowBottomSheet = true
                                    }
                                )
                            }
                        )
                    }
                }
            }

    }

        if (isShowBottomSheet) {
            ModalCleaningDetails(
                cleaningDetails = selectedCleaning.toDetailsState(),
                onDismissRequest = { isShowBottomSheet = false },
                actions = {
                    DoYouLikeService(
                        onAcceptPress = {
                            onAcceptClick(selectedCleaning)
                            isShowBottomSheet = false
                        },
                        onBackPress = { isShowBottomSheet = false }
                    )
                },

                )
        }


        if(isShowDialog){
            ConfirmDialog(
                onDismissRequest = { isShowDialog = false},
                onConfirmPress = {
                    isShowDialog = false
                    isShowAssentment  = true
                },
                onCancelPress = {isShowDialog = false}
            )
        }

        if(isShowAssentment){
            Dialog(onDismissRequest = { isShowAssentment = false }) {
                Card {
                    Text(text = "Avalie aqui")
                }
            }
        }
    }

}

@Composable
fun FindYourCleanings(
    cleanings: List<Cleaning>,
    onCleaningDetail: (Cleaning) -> Unit,
    onAcceptClick: (Cleaning) -> Unit,
    onInfoClick: (Cleaning) -> Unit
) {
    HomeSection(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        title = "Encontre seus serviços",
    ) {
        LazyColumn() {
            item {
                SearchBar()
            }
            items(cleanings) { cleaning ->
                val model = cleaning.toCleaningCardState()
                CleaningCard(
                    dateTime = cleaning.dateTime,
                    nameClient = model.nameClient,
                    servicePrice = model.servicePrice,
                    local = model.local,
                    quantityRooms = model.quantityRooms,
                    actions = {
                        FindCleaningCardActions(
                            onAcceptClick = { onAcceptClick(cleaning) },
                            onInfoClick = { onInfoClick(cleaning) }
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
    modifier: Modifier = Modifier,
    cleanings: List<Cleaning> = fakeCleanings,
    onCleaningDetail: (Cleaning) -> Unit = {},
    onAcceptClick: (Cleaning) -> Unit = {},
    onInfoClick: (Cleaning) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cleanings) { cleaning ->
            val model = cleaning.toCleaningCardState()
            CleaningCard(
                nameClient = model.nameClient,
                servicePrice = model.servicePrice,
                local = model.local,
                quantityRooms = model.quantityRooms,
                actions = {
                    FindCleaningCardActions(
                        onAcceptClick = { onAcceptClick(cleaning) },
                        onInfoClick = { onInfoClick(cleaning) }
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
    cleaningDetails: CleaningDetailsState,
    onDismissRequest: () -> Unit,
    actions: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.paddingFromBaseline(bottom = 56.dp)
    ) {
        CleaningDetails(
            state = cleaningDetails,
            actions = actions
        )
    }
}

