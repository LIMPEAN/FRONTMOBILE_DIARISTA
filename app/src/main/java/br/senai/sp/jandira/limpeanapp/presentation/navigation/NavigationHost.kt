package br.senai.sp.jandira.limpeanapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.presentation.home.HomeScreen
import br.senai.sp.jandira.limpeanapp.presentation.navigation.auth.authGraph
import br.senai.sp.jandira.limpeanapp.presentation.profile.ProfileScreen
import br.senai.sp.jandira.limpeanapp.presentation.ui.SplashScreen


object NavigationRoute {
    const val ONBOARDING = "on_boarding"
    const val AUTHENTICATION = "authentication"
    const val HOME = "home"
    const val PROFILE = "profile"
}
@Composable
fun NavigationHost(
    startDestination : String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.AUTHENTICATION
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
            HomeScreen(
                onSeeProfile ={
                    navController.navigate(NavigationRoute.PROFILE)
                }
            )
        }
        composable(NavigationRoute.PROFILE){
            ProfileScreen()
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