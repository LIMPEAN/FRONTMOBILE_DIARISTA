package br.senai.sp.jandira.limpeanapp.authentication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterHost
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterRoute
import br.senai.sp.jandira.limpeanapp.navigation.NavigationHost
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterProfileScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import br.senai.sp.jandira.limpeanapp.telas.login.LoginScreen


@Composable
fun AuthenticationHost(
    navHostController: NavHostController,
    startDestination : AuthenticationRoute
){
        NavHost(
            navController = navHostController,
            startDestination = startDestination.route
        ) {

            }
        }
//            navigation(
//                route = NavigationRoute.Authentication.route,
//                startDestination = RegisterRoute.Profile.route,
//            ) {
//                composable(route = RegisterRoute.Profile.route){
//                    RegisterProfileScreen(
//                        onNext = {
//                            navHostController.navigate(RegisterRoute.Person.route)
//                        }
//                    )
//                }
//                composable(route = RegisterRoute.Person.route){
//                    RegisterPersonScreen(
//                        onNext = {
//                            navHostController.navigate(RegisterRoute.Person.route)
//                        }
//                    )
//                }
//                composable(route = RegisterRoute.Address.route){
//                    RegisterAddressScreen(
//                        onFinish = {
//                            navHostController.navigate(NavigationRoute.Home.route)
//
//                        }
//                    )
//                }
//                navigation(route = "register", startDestination = "address") {
//                    composable("personal") {
//                        val viewModel = it.sharedViewModel<SignInViewModel>(navHostController)
//
//
//                    }
//                    composable("address") {
//                        val authViewModel = it.sharedViewModel<SignInViewModel>(navController = navHostController)
//                        val cepViewModel = viewModel<CepViewModel>()
//
//                        RegisterScreen(
//                            title = "Adicione um Endereço",
//                            form = { AddressForm(cepViewModel) },
//                            nameButton = "Próximo"
//                        ) {
//                            if (cepViewModel.validateAddress()) {
//                                navHostController.navigate("profile")
//                            }
//                        }
//                    }
//                    composable("profile") {
//                        val authViewModel = it.sharedViewModel<SignInViewModel>(navController = navHostController)
//
//                        RegisterScreen(
//                            title = "Dados Pessoais",
//                            form = {
//                                PersonForm()
//                            },
//                            nameButton = "Próxima"
//                        ){
//
//                        }
//                    }
//
//                    composable("forgot_password") {
//                        val viewModel = it.sharedViewModel<AuthViewModel>(navHostController)
//
//                    }
//                }
//            }
//
//        }



@Preview
@Composable
fun TestAuthenticationRoute() {
    val viewModel = viewModel<SignInViewModel>(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
    val navController = rememberNavController()
    AuthenticationHost(navHostController = navController, startDestination = AuthenticationRoute.ForgotPassword)

}