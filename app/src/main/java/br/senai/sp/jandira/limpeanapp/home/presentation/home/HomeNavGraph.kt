package br.senai.sp.jandira.limpeanapp.home.presentation.home


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.CleaningToStart
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.find_cleaning.FindCleaningServices
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.schedule.ScheduledCleaning
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.see_invites.SeeInvitesAndAlerts
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.your_profile.YourProfile


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





