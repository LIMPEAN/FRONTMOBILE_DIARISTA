package br.senai.sp.jandira.limpeanapp.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeContent
import br.senai.sp.jandira.limpeanapp.feature_diarist.HomeTopBar
import br.senai.sp.jandira.limpeanapp.feature_diarist.notifications.NotificationsScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.schedules.FindYourServicesScreen
import br.senai.sp.jandira.limpeanapp.home.cleaning.components.CleaningSchedules

import br.senai.sp.jandira.limpeanapp.home.cleaning.components.cleanings
import br.senai.sp.jandira.limpeanapp.home.components.HomeRoute
import br.senai.sp.jandira.limpeanapp.home.components.HomeSection
import br.senai.sp.jandira.limpeanapp.home.profile.ProfileScreen
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primaryContainer


@Composable
fun HomeNavGraph(
    modifier : Modifier = Modifier,
    navController : NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute.CLEANING
    ){
        composable(HomeRoute.CLEANING){
            HomeContent(paddingValues = paddingValues) {
                HomeSection(modifier = modifier, title = "Suas Faxinas") {
                    CleaningSchedules(cleanings = cleanings)
                }
            }
        }
        composable(HomeRoute.CLEANING_DETAIL){

        }
        composable(HomeRoute.FIND_CLEANING){
            HomeContent(paddingValues = paddingValues) {
                HomeSection(modifier = modifier, title = "Encontre Serviços") {
                    CleaningSchedules(cleanings = cleanings)
                }
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

@Preview
@Composable
fun Test() {
    val paddingValues = PaddingValues(
        20.dp
    )
    val modifier = Modifier.padding(24.dp)
    LimpeanAppTheme {
        HomeContent(paddingValues = paddingValues) {
            HomeSection(modifier = modifier, title = "Encontre Serviços") {
                //Componente de Busca
                TextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {})
                CleaningSchedules(cleanings = cleanings)
            }
        }
    }
    
}