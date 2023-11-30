package br.senai.sp.jandira.limpeanapp.presentation.features.schedule

import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toCleaningCardState
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GetImageFromGoogleMap
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GetPropertiesForGoogleMapUseCase
import br.senai.sp.jandira.limpeanapp.core.domain.usecases.GoogleMapState
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeContent
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.core.presentation.components.HomeSection
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.feature_diarist.token.GenerateTokenContent
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.LoadingDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.ModalCleaningDetails
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.presentation.features.components.CleaningCard
import br.senai.sp.jandira.limpeanapp.presentation.features.components.GoogleMapContainer
import br.senai.sp.jandira.limpeanapp.presentation.features.components.ImageMap
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_tertiary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalTime
import javax.inject.Inject


@Composable
fun ScheduleScreen(
    viewModel : ScheduleViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val startServiceState = viewModel.startServiceState.value
    val context = LocalContext.current

    var isShowDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(state.message) {
        if(state.message.isNotBlank()){
            Toast.makeText(
                context,
                state.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    if(startServiceState.isLoading){
        LoadingDialog()
    }
    ScheduleContent(
        cleanings = state.cleanings,
        onStartClick = {
            viewModel.onStartService(it)
            isShowDialog = true
        }
    )


    if(startServiceState.isLoading){
        LoadingDialog()
    }
    if(isShowDialog){
        Dialog(
            onDismissRequest = {
                viewModel.getScheduled()
                isShowDialog = false
            }
        ) {
            GenerateTokenContent(
                onBackPress = {isShowDialog = false},
                token = startServiceState.token
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun ScheduleContent(
    cleanings : List<Cleaning> = listOf(
        fakeCleanings[0],
        fakeCleanings[1],
        fakeCleanings[0],
        fakeCleanings[1],
        fakeCleanings[0],
        fakeCleanings[1],
        fakeCleanings[0],
        fakeCleanings[1],
    ),
    onStartClick: (Cleaning) -> Unit = {},

){
    val context = LocalContext.current
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
            SchedulesContent(
                cleanings = cleanings,
                onCleaningDetail = {
                    cleaning = it
                    isShowBottomSheet = true },
                onStartClick = {
                    onStartClick(it)
                },
                onInfoClick = {
                    cleaning = it
                    isShowBottomSheet = true
                }
            )
        }
        if(isShowBottomSheet){
            ModalCleaningDetails(
                cleaningDetails = cleaning.toDetailsState(),
                onDismissRequest = { isShowBottomSheet = false},
                actions = {}
            )
        }
    }
}

@Composable
fun SchedulesContent(
    paddingValues: PaddingValues = PaddingValues(24.dp),
    cleanings : List<Cleaning>,
    onCleaningDetail: (Cleaning) -> Unit,
    onStartClick : (Cleaning) -> Unit,
    onInfoClick : (Cleaning) -> Unit,
) {
    if(cleanings.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CleaningNotFound()
        }
    }
    HomeSection(
        modifier = Modifier.padding(paddingValues),
        title = "Próximos Serviços",
    ) {
        ScheduleList(
            cleanings = cleanings,
            onStartClick = { onStartClick(it) },
            onInfoClick = { onInfoClick(it) },
            onCleaningDetail = { onCleaningDetail(it)},
        )
    }
}
@HiltViewModel
class MapViewModel @Inject constructor(
    private val getPropertiesForMapUseCase : GetPropertiesForGoogleMapUseCase
) : ViewModel(){

    var state by mutableStateOf(GoogleMapState())
        private set

    fun onLoadingMap(cleaning : Cleaning){
        getPropertiesForMapUseCase(cleaning).onEach {result ->
            state = when(result){
                is Resource.Success ->{
                    result.data?: GoogleMapState()
                }

                is Resource.Error -> {
                    GoogleMapState()

                }

                is Resource.Loading -> {
                    GoogleMapState(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }
}
@HiltViewModel
class ImageMapViewModel @Inject constructor(
    private val getImageFromGoogleMap: GetImageFromGoogleMap
) : ViewModel(){

    var state by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun onLoadingImage(address: Address ){
        getImageFromGoogleMap(address).onEach {result ->
            when(result){
                is Resource.Success -> {
                    state = result.data!!
                }
                is Resource.Error -> {
                    message = result.message?: "Algum erro aconteceu."
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }

        }.launchIn(viewModelScope)
    }
}
@Preview
@Composable
fun ScheduleList(
    modifier : Modifier = Modifier,
    cleanings : List<Cleaning> = fakeCleanings,
    onCleaningDetail : (Cleaning) -> Unit = {},
    onStartClick: (Cleaning) -> Unit ={},
    onInfoClick: (Cleaning) -> Unit ={},
//    mapViewModel : MapViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(cleanings){cleaning ->
            val model = cleaning.toCleaningCardState()
            val address = cleaning.address
//            var googleMap by remember {
//                mutableStateOf(mapViewModel.state)
//            }
            CleaningCard(
                mapContainer = {
//                    mapViewModel.onLoadingMap(cleaning)
//                    if(googleMap.isLoading){
//                        Box(modifier = Modifier
//                            .fillMaxWidth()
//                            .height(150.dp)){
//                            CircularProgressIndicator()
//                        }
//                    }
//                    if(googleMap.local != null){
//                        GoogleMapContainer(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp),
//                            local = googleMap.local!!, name = googleMap.name, place = googleMap.place)
//                    }
                },
                quantityRooms = cleaning.details.roomsQuantity,
                dateTime = cleaning.dateTime,
                nameClient = model.nameClient,
                servicePrice = model.servicePrice,
                local = "${address.street}, ${address.number} - ${address.district}, ${address.state}",
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
                fontFamily = Poppins,
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
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsButtonPreview() {
    LimpeanAppTheme {
        SeeDetailsButton({})
    }
}
@Composable
fun SeeDetailsButton(
    onInfoClick : () -> Unit
) {
    Button(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp , MaterialTheme.colorScheme.tertiary),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onInfoClick()
        }) {
        Text(
            text = "Ver detalhes",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = Poppins,
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
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Procure por novas faxinas!",
                style= MaterialTheme.typography.bodyMedium,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

    }
}



