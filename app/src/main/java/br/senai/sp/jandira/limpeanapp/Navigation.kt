package br.senai.sp.jandira.limpeanapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.Destinations.WELCOME_ROUTE
import br.senai.sp.jandira.limpeanapp.ui.welcome.WelcomeRoute

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val SIGN_UP_ROUTE = "signup/{userType}"
    const val SIGN_IN_ROUTE = "signin/{userType}"
    const val DASHBOARD_ROUTE = "dashboard/{user}"
}

@Composable
fun LimpeanNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToSignIn = {
                    navController.navigate("signin/$it")
                },
                onNavigateToSignUp = {
                    navController.navigate("signup/$it")
                }

            )
        }
    }
}

