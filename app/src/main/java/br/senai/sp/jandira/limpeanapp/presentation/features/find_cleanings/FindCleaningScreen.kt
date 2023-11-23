package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeContent
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeSection
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.DoYouLikeService
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


    val state = viewModel.state.value
    val diaristState = viewModel.getDiaristState.value
    val context = LocalContext.current
    val selectedCleaning = viewModel.selectedCleaning.value

    LaunchedEffect(state.message) {
        if(state.message.isNotBlank()){
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
    if(state.isLoading){
        LoadingDialog()
    }
}


@Preview
@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { /*TODO*/ }) {
        Box (contentAlignment = Alignment.Center){
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkMode")
@Composable
fun FindCleaningPreview() {
    LimpeanAppTheme {
        FindCleaningContent(
            nameUser = "TEste",
            cleanings = emptyList(),
            isLoadingCleaning = false,
            isLoadingDiarist = false,
            onInfoClick = {},
            onAcceptClick = {},
            onCleaningDetail = {},
        )
    }
}
@Composable
fun FindCleaningContent(
    nameUser : String = "Felipe",
    cleanings : List<Cleaning> = fakeCleanings,
    onCleaningDetail: (Cleaning) -> Unit,
    onInfoClick: (Cleaning) -> Unit,
    onAcceptClick: (Cleaning) -> Unit,
    isLoadingCleaning : Boolean,
    isLoadingDiarist: Boolean,
    selectedCleaning : Cleaning = Cleaning()
){
    var cleaning by remember {
        mutableStateOf(Cleaning())
    }
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    HomeLayout(
        topBar = {
            if(isLoadingDiarist){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                        .fillMaxWidth()
                ){
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
            if(isLoadingCleaning) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center)
                {
                    CircularProgressIndicator()
                }
            }
            if(cleanings.isEmpty()){
                Box(modifier = Modifier.fillMaxSize()){
                    CleaningNotFound()
                }
            }
                FindYourCleanings(
                    cleanings = cleanings,
                    onCleaningDetail = {
                        onCleaningDetail(it)
                        isShowBottomSheet = true
                    },
                    onAcceptClick = {
                        isShowBottomSheet = false
                        onAcceptClick(it)
                    },
                    onInfoClick = {
                        onInfoClick(it)
                        isShowBottomSheet = true
                    }
                )
            }

        }

    if(isShowBottomSheet){
        ModalCleaningDetails(
            cleaningDetails = selectedCleaning.toDetailsState(),
            onDismissRequest = { isShowBottomSheet = false},
            actions = {
                DoYouLikeService(
                    onAcceptPress = {
                        onAcceptClick(selectedCleaning)
                        isShowBottomSheet = false
                    },
                    onBackPress = { isShowBottomSheet = false}
                )
            },

        )
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
                    dateTime = cleaning.dateTime,
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
    actions : @Composable () -> Unit
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

