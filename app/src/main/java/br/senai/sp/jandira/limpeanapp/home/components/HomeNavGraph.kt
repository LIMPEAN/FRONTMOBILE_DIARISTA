package br.senai.sp.jandira.limpeanapp.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning.CleanScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning.CleaningDetailScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.notifications.NotificationsScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.schedules.FindYourServicesScreen
import br.senai.sp.jandira.limpeanapp.home.cleaning.CleaningScreen
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.cleanings
import br.senai.sp.jandira.limpeanapp.home.profile.ProfileScreen
import com.example.compose.md_theme_light_primaryContainer


@Composable
fun HomeNavGraph(
    navController : NavHostController,
    startDestination : String
) {
    NavHost(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(md_theme_light_primaryContainer)
        ,
        navController = navController,
        startDestination = startDestination
    ){
        composable(HomeRoute.CLEANING){
            CleaningScreen(cleanings = cleanings)
        }
        composable(HomeRoute.CLEANING_DETAIL){
            CleaningDetailScreen()
        }
        composable(HomeRoute.FIND_CLEANING){
            Box (contentAlignment = Alignment.Center){
                Text(text = "ChoseRequest")
            }
        }
        composable(HomeRoute.SCHEDULES){
            FindYourServicesScreen()
        }
        composable(HomeRoute.NOTIFICATIONS){
            NotificationsScreen()
        }
        composable(HomeRoute.PROFILE){
            ProfileScreen()
        }
        composable("test"){
            Text(text = "Testando")
        }

    }
}