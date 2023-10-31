package br.senai.sp.jandira.limpeanapp.feature_diarist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning.CleanScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning.CleaningDetailScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.notifications.NotificationsScreen
import br.senai.sp.jandira.limpeanapp.feature_diarist.schedules.FindYourServicesScreen
import br.senai.sp.jandira.limpeanapp.home.profile.ProfileScreen
import com.example.compose.LimpeanAppTheme
import com.example.compose.seed


@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            HomeNavBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(HomeRoute.FIND_CLEANING)},
                containerColor = seed,
                contentColor = Color(251,251,251)
            ) {
                Icon(Icons.Filled.Search, "Small floating action button.")
            }
        }
    ) {
        val paddingValues = it
        NavHost(navController = navController, startDestination = HomeRoute.CLEANING){
            composable(HomeRoute.CLEANING){
                CleanScreen {
                    navController.navigate(HomeRoute.CLEANING_DETAIL)
                }
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
        }
    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    LimpeanAppTheme {
        HomeScreen()
    }
}