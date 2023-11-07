package br.senai.sp.jandira.limpeanapp.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.navigation.HomeNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        bottomBar = {
            HomeTopBar(navController = navController)
        },
    ) {
        HomeNavGraph(navController = navController)
    }

}






