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
import br.senai.sp.jandira.limpeanapp.telas.AuthViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.PersonForm
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.AddressForm
import br.senai.sp.jandira.limpeanapp.telas.login.LoginScreen
import com.example.compose.LimpeanAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth") {
                    navigation(
                        startDestination = "register",
                        route = "auth"
                    ) {
                        composable("login") {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController)

                            LoginScreen(
                                onClickToLogin = {
                                    navController.navigate("home") {
                                        popUpTo("auth") {
                                            inclusive = true
                                        }
                                    }
                                }
                            )

                        }
                        navigation(route = "register", startDestination = "address") {
                            composable("personal") {
                                val viewModel = it.sharedViewModel<AuthViewModel>(navController)

                            }
                            composable("address") {
                                val authViewModel =
                                    it.sharedViewModel<AuthViewModel>(navController = navController)
                                val cepViewModel = viewModel<CepViewModel>()
                                RegisterScreen(
                                    title = "Adicione um Endereço",
                                    form = { AddressForm(cepViewModel) },
                                    nameButton = "Próximo"
                                ) {
                                    if (cepViewModel.validateAddress()) {
                                        navController.navigate("profile")
                                    }
                                }
                            }
                            composable("profile") {
                                val authViewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
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
                                val viewModel = it.sharedViewModel<AuthViewModel>(navController)

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

