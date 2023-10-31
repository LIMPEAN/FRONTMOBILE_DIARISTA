package br.senai.sp.jandira.limpeanapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.authGraph
import br.senai.sp.jandira.limpeanapp.core.presentation.SplashScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeScreen
import br.senai.sp.jandira.limpeanapp.home.HomeViewModel

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.ONBOARDING
    ){
        composable(NavigationRoute.ONBOARDING){
            SplashScreen {
                navController.navigate(NavigationRoute.AUTHENTICATION)
            }
        }
        authGraph(navController)
        composable(NavigationRoute.HOME){
            val vm = hiltViewModel<HomeViewModel>()
            vm.findDiarist()
            HomeScreen()
        }
    }
}