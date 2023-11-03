package br.senai.sp.jandira.limpeanapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.ui.features.schedules.SchedulesScreen
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.FindCleaningServices
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.ScheduledCleaning
import br.senai.sp.jandira.limpeanapp.ui.features.notifications.SeeInvitesAndAlerts
import br.senai.sp.jandira.limpeanapp.ui.features.profile.YourProfile


object HomeRoute {
    const val CLEANING = "cleaning"
    const val SCHEDULES = "schedules"
    const val FIND_CLEANING = "find-cleaning"
    const val PROFILE = "profile"
    const val NOTIFICATIONS = "notifications"
}
@Composable
fun HomeNavGraph(
    navController : NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.CLEANING
    ){
        composable(HomeRoute.CLEANING){
            SchedulesScreen()
        }
        composable(HomeRoute.FIND_CLEANING){
            FindCleaningServices()
        }
        composable(HomeRoute.SCHEDULES){
            ScheduledCleaning()
        }
        composable(HomeRoute.NOTIFICATIONS){
            SeeInvitesAndAlerts()
        }
        composable(HomeRoute.PROFILE){
            YourProfile()
        }
    }
}





