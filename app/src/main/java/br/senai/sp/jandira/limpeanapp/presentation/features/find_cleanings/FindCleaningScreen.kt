package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GetImageFromGoogleMap
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GoogleMapState
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
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
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningListState
import br.senai.sp.jandira.limpeanapp.presentation.features.components.FindCleaningCardActions
import br.senai.sp.jandira.limpeanapp.presentation.features.components.ImageMap
import br.senai.sp.jandira.limpeanapp.presentation.features.components.StartedCleaningActions
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.ui.components.ImageGoogleMap
import br.senai.sp.jandira.limpeanapp.ui.components.dialog.AssentmentDialog
import br.senai.sp.jandira.limpeanapp.ui.components.dialog.AssentmentState
import com.example.compose.LimpeanAppTheme
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindCleaningScreen(
    viewModel: FindCleaningViewModel = hiltViewModel()
) {


    val state = viewModel.state
    val assentmentState = viewModel.assentmentState.value
    val getDiaristState = viewModel.getDiaristState.value
    val startedServicesState = viewModel.startedServices.value
    val context = LocalContext.current
    val assentment = viewModel.assentmentState.value


    var isShowDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(viewModel.uiEvent){
        viewModel.uiEvent.collect{result ->
            when(result){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        result.message,
                        Toast.LENGTH_LONG
                    ).show()
                    isShowDialog = false
                }
                is UiEvent.ShowDialogLoading -> {
                    isShowDialog = true
                }
                else -> {}
            }
        }
    }

    val getStartedServices = CleaningListState(
            listName = "Em andamento",
            cleanings = startedServicesState.cleanings,
            isLoading = startedServicesState.isLoading
    )
    val getOpenServices = CleaningListState(
        listName = "Encontre suas faxinas.",
        cleanings = state.openServices,
        isLoading = state.isLoading
    )



    FindCleaningContent(
        getDiaristState = getDiaristState,
        getStartedServices = getStartedServices,
        getOpenServices = getOpenServices,
        onAcceptClick = {
            viewModel.onEvent(FindCleaningEvent.OnAcceptClick(it))
        },
        onFinishedCleaning = {
            viewModel.onEvent(FindCleaningEvent.OnClickFinishedService(it))
        },
        onAssentment = {
            viewModel.onEvent(FindCleaningEvent.OnAssentment(it))
        },
        assentent = assentment
    )
    if (isShowDialog) {
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
        var googleMapState by remember {
            mutableStateOf(GoogleMapState())
        }
        FindCleaningContent(
            onAcceptClick = {},
            onFinishedCleaning = {},
            onAssentment = {}
        )
    }
}

data class GetLatLgnState(
    val url : String? = null,
    val isLoading : Boolean = false,
    val message : String = "",
    val latLgn : LatLng? = null
)
@HiltViewModel
class GetLatLgnForMapViewModel @Inject constructor(
    private val getImageFromGoogleMap: GetImageFromGoogleMap
): ViewModel(){



    var state by mutableStateOf(GetLatLgnState())
        private set
    fun getLatLgn(address: Address){
        getImageFromGoogleMap(address).onEach {result ->
            when(result){
                is Resource.Success -> {
                    val latLgn = result.data
                    val urlGoogleStreet = "https://maps.googleapis.com/maps/api/streetview?size=500x500&location=${latLgn?.latitude},${latLgn?.longitude}&key=AIzaSyCLXaX9MfiGsaZOonUpXtsfC6CN8AaRZcE"
                    state = GetLatLgnState(
                        latLgn = latLgn
                    )
                }
                is Resource.Error -> {
                    state = GetLatLgnState(result.message)
                }
                is Resource.Loading -> {
                    state = GetLatLgnState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FindCleaningContent(
    getDiaristState: GetDiaristState = GetDiaristState(),
    getStartedServices : CleaningListState = CleaningListState(),
    getOpenServices : CleaningListState = CleaningListState(),
    onAcceptClick: (Cleaning) -> Unit,
    onFinishedCleaning : (Cleaning) -> Unit,
    assentent : AssentmentState = AssentmentState(),
    onAssentment : (AssentmentState)-> Unit,
    getLatLgnForMap : GetLatLgnForMapViewModel = hiltViewModel()
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
    var isAcceptAction by remember {
        mutableStateOf(false)
    }

    HomeLayout(
        topBar = {
            FindCleaningTopBar(getDiaristState = getDiaristState)
        }
    ){ paddingValues ->
        HomeContent(paddingValues) {
            if (getOpenServices.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    CircularProgressIndicator()
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(18.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                item {
                    Text(
                        text = getStartedServices.listName,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                items(getStartedServices.cleanings){startedService ->
                    if (getStartedServices.cleanings.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CleaningNotFound()
                        }
                    }

                    val address = startedService.address

                    CleaningCard(
                        mapContainer = {
                            ImageGoogleMap(address = address)

                        },
                        id = startedService.id,
                        nameClient = startedService.client.name,
                        servicePrice = startedService.price?: 0.0,
                        dateTime = startedService.dateTime,
                        local = "${address.street}, ${address.number} - ${address.city}, ${address.state}",
                        quantityRooms = startedService.details.roomsQuantity,
                        onCleaningDetail = {
                            selectedCleaning = startedService
                            isShowBottomSheet = true
                        },
                        actions = {
                            StartedCleaningActions(
                                cleaning = startedService,
                                onFinished = {
                                    selectedCleaning = it
                                    isShowDialog = true
                                },
                                onInfoClick = {
                                    selectedCleaning = it
                                    isShowBottomSheet = true
                                }
                            )
                        }
                    )
                }
                item {
                    Text(
                        text = getOpenServices.listName,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                items(getOpenServices.cleanings){cleaning ->
                    if (getOpenServices.cleanings.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CleaningNotFound()
                        }
                    }
                    val address = cleaning.address

                    CleaningCard(
                        id = cleaning.id,
                        mapContainer = {
                            ImageGoogleMap(address = address)
                        },
                        nameClient = cleaning.client.name,
                        servicePrice = cleaning.price ?: 0.0,
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
                                onAcceptClick = {
                                    selectedCleaning = cleaning
                                    isAcceptAction = true
                                    isShowDialog = true
                               },
                                onInfoClick = {
                                    selectedCleaning = cleaning
                                    isShowBottomSheet = true
                                }
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(80.dp))

    }
    }

    if (isShowBottomSheet) {
        ModalCleaningDetails(
            cleaningDetails = selectedCleaning.toDetailsState(),
            onDismissRequest = { isShowBottomSheet = false },
            actions = {
                DoYouLikeService(
                    onAcceptPress = {
                        isShowBottomSheet = false
                        isAcceptAction = true
                        isShowDialog = true
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
                if(isAcceptAction){
                    onAcceptClick(selectedCleaning)
                }else{
                    onFinishedCleaning(selectedCleaning)
                    isShowAssentment = true
                }
                isShowBottomSheet = false
                isShowDialog = false

            },
            onCancelPress = {isShowDialog = false}
        )
    }


    if(isShowAssentment){
        AssentmentDialog(
            name = selectedCleaning.client.name,
            profileImage = selectedCleaning.client.photo,
            onAssentment = {
                onAssentment(
                    AssentmentState(
                        stars = it.stars,
                        comment = it.comment,
                        client = selectedCleaning.client,
                    )
                )
                isShowAssentment = false
            },
            onCancel = { isShowAssentment = false},
        )
    }
    if(assentent.isLoading){
        LoadingDialog()
    }

}

@Composable
fun FindCleaningTopBar(
    getDiaristState: GetDiaristState
) {
    val diarist = getDiaristState.diarist
    if (getDiaristState.isLoading) {
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
            titleLarge = diarist.name
        )
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
    googleMapState : GoogleMapState = GoogleMapState(),
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
                mapContainer = {
                    ImageMap()
                },
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

