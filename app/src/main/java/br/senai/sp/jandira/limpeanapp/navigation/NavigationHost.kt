package br.senai.sp.jandira.limpeanapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.limpeanapp.navigation.auth.authGraph
import br.senai.sp.jandira.limpeanapp.ui.SplashScreen
import br.senai.sp.jandira.limpeanapp.home.HomeScreen


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
        startDestination = NavigationRoute.HOME
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
//        composable(
//            route = HomeRoute.CLEANING + "?cleaningId={cleaningId}",
//            arguments = listOf(
//                navArgument(name = "cleaningId") {
//                    type = NavType.IntType
//                    defaultValue = -1
//                }
//            )
//        ){
//            CleaningDetailsScreen(onPopBackStack = {
//                navController.popBackStack()
//            })
//
//        }
    }
}