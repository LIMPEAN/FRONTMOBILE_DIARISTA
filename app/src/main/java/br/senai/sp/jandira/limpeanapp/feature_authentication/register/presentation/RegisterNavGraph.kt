package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.RegisterResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.toDomain
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormUi
import br.senai.sp.jandira.limpeanapp.ui.navigation.NavigationRoute
import com.example.compose.LimpeanAppTheme
import kotlinx.coroutines.delay

object RegisterRoute{
    val Profile = "profile"
    val Address = "address"
}

fun NavGraphBuilder.registerNavGraph(navController : NavHostController) {
    navigation(
        route = "register",
        startDestination = RegisterRoute.Profile
    ){
        composable(RegisterRoute.Profile){entry ->
            val viewModel = entry.sharedViewModel<RegisterViewModel>(navController)
            val state = viewModel.createProfileFormState()
            
            RegisterScreen(
                titleSection = {
                    TitleSection(
                        horizontal = Alignment.CenterHorizontally,
                        title = stringResource(R.string.register_title),
                        description = stringResource(R.string.register_description)
                    )
                },
                onButtonClick = {
                    viewModel.save(profileState = state)
                    navController.navigate(RegisterRoute.Address)
                },
                titleButton = stringResource(R.string.button_register_profile)
            ) {
                ProfileFormUi(
                    profilePhoto = { /*TODO*/ },
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
        composable(RegisterRoute.Address){entry ->
            val viewModel = entry.sharedViewModel<RegisterViewModel>(navController)
            val state = viewModel.createAddressFormState()
            val context = LocalContext.current

            LaunchedEffect(viewModel, context){
                    viewModel.registerResult.collect{ result->
                            when(result){
                                is RegisterResult.Successful ->{
                                    Toast.makeText(
                                        context,
                                        "Deu certo. Encaminhando para login",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    delay(2000)
                                    navController.navigate(NavigationRoute.Home.route)
                                }
                                is RegisterResult.Error -> {
                                    Toast.makeText(
                                        context,
                                        "Um erro aconteceu: ${result.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
            }


            RegisterScreen(
                titleSection = {
                    TitleSection(
                        horizontal = Alignment.CenterHorizontally,
                        title = stringResource(R.string.register_address_title),
                        description = stringResource(R.string.register_address_description)
                    )
                },
                titleButton = "Finalizar",
                onButtonClick = {
                    viewModel.save(address = state.toDomain())
                    viewModel.saveDiarist()
                },
            ) {
                AddressFormUi(
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .padding(20.dp),
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

@Preview
@Composable
fun TesteNavRegistration() {
    val navController = rememberNavController()
    LimpeanAppTheme {
        NavHost(navController = navController, startDestination = "register"){
            registerNavGraph(navController)
        }
    }
}

