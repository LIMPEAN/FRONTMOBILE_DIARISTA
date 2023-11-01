package br.senai.sp.jandira.limpeanapp.home


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.home.presentation.cleaning.CleaningToStart
import br.senai.sp.jandira.limpeanapp.home.presentation.find_cleaning.FindCleaningServices
import br.senai.sp.jandira.limpeanapp.home.presentation.schedule.ScheduledCleaning
import br.senai.sp.jandira.limpeanapp.home.presentation.see_invites.SeeInvitesAndAlerts
import br.senai.sp.jandira.limpeanapp.home.presentation.your_profile.YourProfile


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
            CleaningToStart()
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





