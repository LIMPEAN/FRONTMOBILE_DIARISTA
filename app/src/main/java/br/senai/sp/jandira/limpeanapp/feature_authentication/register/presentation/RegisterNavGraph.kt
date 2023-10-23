package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.usecase.RegisterResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.toDomain
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormUi
import com.example.compose.LimpeanAppTheme
import kotlinx.coroutines.delay

object RegisterRoute{
    val Profile = "profile"
    val Address = "address"
}

fun NavGraphBuilder.registerNavGraph(navController : NavController) {
    navigation(
        route = "register",
        startDestination = RegisterRoute.Profile
    ){
        composable(RegisterRoute.Profile){
            val viewModel = hiltViewModel<RegisterViewModel>()
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
        composable(RegisterRoute.Address){
            val viewModel = hiltViewModel<RegisterViewModel>()
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
                                    navController.navigate("Home")
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
                onButtonClick = {
                    viewModel.save(address = state.toDomain())
                    viewModel.saveDiarist()
                },
                titleSection = {
                    TitleSection(
                        horizontal = Alignment.CenterHorizontally,
                        title = stringResource(R.string.register_address_title),
                        description = stringResource(R.string.register_address_description)
                    )
                },
                titleButton = "Finalizar"
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




