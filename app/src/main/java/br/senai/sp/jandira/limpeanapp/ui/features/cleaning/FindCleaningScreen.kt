package br.senai.sp.jandira.limpeanapp.ui.features.cleaning

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.toDetailsState
import br.senai.sp.jandira.limpeanapp.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.ui.components.HomeContent
import br.senai.sp.jandira.limpeanapp.ui.components.HomeLayout
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningDetails
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data.cleanings
import br.senai.sp.jandira.limpeanapp.ui.features.schedules.CleaningNotFound
import br.senai.sp.jandira.limpeanapp.ui.features.schedules.YourCleanings
import br.senai.sp.jandira.limpeanapp.ui.features.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindCleaningScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel : FindCleaningViewModel = hiltViewModel()
) {

    val cleanings = viewModel.cleanings.collectAsState(initial = emptyList())
    val nameUser = viewModel.emailUser

    val snackbarHostState = remember { SnackbarHostState() }

    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
//                    actionif(result == SnackbarResult.ActionPerformed) {
//                        viewModel.onEvent(FindCleaningEvent.OnCleaningInfoClick(Cle))
//                    }

                }
                is UiEvent.ShowBottomSheet -> {
                    isShowBottomSheet = true
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    HomeLayout(
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                title = "Bem vindo (a)",
                description = nameUser
            )
        }
    ) { paddingValues ->

        HomeContent(paddingValues) {
            if (cleanings.value.isEmpty()) {
                CleaningNotFound()
            }
            YourCleanings(
                cleanings = cleanings.value,
                onCleaningDetail = {
                    viewModel.onEvent(FindCleaningEvent.OnCleaningClick(it))
                },
                onCancel = {},
                onStart = {}
            )

        }
        if(isShowBottomSheet){
            val cleaningDetails = viewModel.selectedCleaning.toDetailsState()
            ModalBottomSheet(onDismissRequest = { isShowBottomSheet =  false}) {
                CleaningDetails(
                    state = cleaningDetails,
                    onAcceptPress = {
                          viewModel.onEvent(FindCleaningEvent.OnAcceptClick(viewModel.selectedCleaning))
                    },
                    onBackPress = {
                        isShowBottomSheet = false
                    }
                )
            }
        }
    }
}

