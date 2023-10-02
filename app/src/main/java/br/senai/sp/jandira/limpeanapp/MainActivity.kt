package br.senai.sp.jandira.limpeanapp

import CepViewModel
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute
import br.senai.sp.jandira.limpeanapp.navigation.RegisterRoute
import br.senai.sp.jandira.limpeanapp.telas.AuthViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterAddressScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterProfileScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterRoute
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.PersonForm
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.AddressForm
import br.senai.sp.jandira.limpeanapp.telas.login.LoginScreen
import com.example.compose.LimpeanAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = NavigationRoute.Authentication.route) {
                    navigation(
                        route = NavigationRoute.Authentication.route,
                        startDestination = RegisterRoute.Profile.route,
                    ) {
                        composable(route = RegisterRoute.Profile.route){
                            RegisterProfileScreen(
                                onNext = {
                                    navHostController.navigate(RegisterRoute.Person.route)
                                }
                            )
                        }
                        composable(route = RegisterRoute.Person.route){
                            RegisterPersonScreen(
                                onNext = {
                                    navHostController.navigate(RegisterRoute.Person.route)
                                }
                            )
                        }
                        composable(route = RegisterRoute.Address.route){
                            RegisterAddressScreen(
                                onFinish = {
                                    navHostController.navigate(NavigationRoute.Home.route)

                                }
                            )
                        }
                        navigation(route = "register", startDestination = "address") {
                            composable("personal") {
                                val viewModel = it.sharedViewModel<SignInViewModel>(navHostController)


                            }
                            composable("address") {
                                val authViewModel = it.sharedViewModel<SignInViewModel>(navController = navHostController)
                                val cepViewModel = viewModel<CepViewModel>()

                                RegisterScreen(
                                    title = "Adicione um Endereço",
                                    form = { AddressForm(cepViewModel) },
                                    nameButton = "Próximo"
                                ) {
                                    if (cepViewModel.validateAddress()) {
                                        navHostController.navigate("profile")
                                    }
                                }
                            }
                            composable("profile") {
                                val authViewModel = it.sharedViewModel<SignInViewModel>(navController = navHostController)

                                RegisterScreen(
                                    title = "Dados Pessoais",
                                    form = {
                                        PersonForm()
                                    },
                                    nameButton = "Próxima"
                                ){

                                }
                            }

                            composable("forgot_password") {
                                val viewModel = it.sharedViewModel<AuthViewModel>(navHostController)

                            }
                        }
                    }

                }
            }
        }
    }
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

