package br.senai.sp.jandira.limpeanapp.core

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.core.navigation.NavigationRoute
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.LoginScreen
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.registerNavGraph

fun NavGraphBuilder.authGraph(navController: NavHostController){
    navigation(
        route = NavigationRoute.AUTHENTICATION,
        startDestination = AuthenticationRoute.LOGIN
    ){
        composable(AuthenticationRoute.LOGIN){
            LoginScreen(
                onLogin = {
                    navController.navigate(NavigationRoute.HOME){
                        popUpTo(NavigationRoute.HOME) {
                            inclusive = true
                        }
                    }
                },
                onRegister = {
                    navController.navigate(AuthenticationRoute.REGISTER)
                }
            )
        }
        registerNavGraph(navController)
        composable(AuthenticationRoute.FORGOT_PASSWORD){}
    }
}