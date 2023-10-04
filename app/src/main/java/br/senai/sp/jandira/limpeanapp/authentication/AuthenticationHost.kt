package br.senai.sp.jandira.limpeanapp.authentication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.authentication.register.SignInViewModel


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