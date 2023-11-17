package br.senai.sp.jandira.limpeanapp.presentation.navigation


import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.core.presentation.util.UiEvent
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.FindCleaningScreen
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.invites.NotificationsScreen
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.SettingsScreen
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.YourProfile
import br.senai.sp.jandira.limpeanapp.presentation.features.schedule.ScheduleScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


object HomeRoute {
    const val CLEANING = "cleaning"
    const val SCHEDULE = "schedule"
    const val INVITES = "invites"
    const val PROFILE = "profile"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.CLEANING
    ){
        composable(HomeRoute.CLEANING){
            FindCleaningScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(HomeRoute.SCHEDULE){
            ScheduleScreen()
        }
        composable(HomeRoute.INVITES){
            Text(text = "Invites Screen")
        }
        composable(HomeRoute.PROFILE){
            SettingsScreen()
        }

    }
}


sealed class CleaningDetailsEvent {
    object OnAceptedClick: CleaningDetailsEvent()
}

@HiltViewModel
class CleaningDetailsViewModel @Inject constructor(
    private val repository : CleaningRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    var cleaning by mutableStateOf<Cleaning?>(null)
        private set


    var cleaningDetails by mutableStateOf(CleaningDetailsState())
        private set
    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        val cleaningId = savedStateHandle.get<Int>("cleaningId")!!
        if(cleaningId != -1) {
            viewModelScope.launch {
                repository.getCleaningDetail(cleaningId)?.let { cleaning ->
                    cleaningDetails = cleaning.toDetailsState()
                }
            }
        }
    }
}
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun CleaningDetailsScreen(
//    onPopBackStack: () -> Boolean,
//    viewModel : CleaningDetailsViewModel = hiltViewModel()
//) {
//
//    //scaffold state
//    val snackbarHostState = remember { SnackbarHostState() }
//
//    LaunchedEffect(key1 = true) {
//        viewModel.uiEvent.collect { event ->
//            when(event) {
//                is UiEvent.PopBackStack -> onPopBackStack()
//                is UiEvent.ShowSnackbar -> {
//                    snackbarHostState.showSnackbar(
//                        message = event.message,
//                        actionLabel = event.action
//                    )
//                }
//                else -> Unit
//            }
//        }
//    }
//    Scaffold(
//        snackbarHost = {
//            SnackbarHost (snackbarHostState)
//        }
//    ) {
//        CleaningDetails(
//            state = viewModel.cleaningDetails
//        )
//    }
//
//}





