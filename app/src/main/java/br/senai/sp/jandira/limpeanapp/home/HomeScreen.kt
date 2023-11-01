package br.senai.sp.jandira.limpeanapp.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.home.components.HomeTopBar


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel : HomeViewModel = hiltViewModel()
) {

    HomeScreen(
        navController = navController
    ){
        HomeNavGraph(navController = navController)
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeScreen(
    navController: NavHostController,
    screenContent : @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            HomeTopBar(navController = navController)
        }
    ) {
        screenContent()
    }
}






