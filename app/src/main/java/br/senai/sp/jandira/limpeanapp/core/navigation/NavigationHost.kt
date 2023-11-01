package br.senai.sp.jandira.limpeanapp.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.presentation.SplashScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeScreen



object NavigationRoute {
    const val ONBOARDING = "on_boarding"
    const val AUTHENTICATION = "authentication"
    const val HOME = "home"
    const val SETTINGS = "settings"
}
@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.ONBOARDING
    ){
        composable(NavigationRoute.ONBOARDING){
            SplashScreen {
                            navController.navigate(NavigationRoute.AUTHENTICATION){
                                popUpTo(NavigationRoute.ONBOARDING){
                                    inclusive = true
                                }
                }
            }
        }
        authGraph(navController)
        composable(NavigationRoute.HOME){
            HomeScreen()
        }
    }
}