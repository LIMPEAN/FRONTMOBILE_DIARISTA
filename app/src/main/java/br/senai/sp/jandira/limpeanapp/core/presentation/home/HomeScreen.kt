package br.senai.sp.jandira.limpeanapp.core.presentation.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.presentation.navigation.HomeNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    onSeeProfile : () -> Unit,
) {

    Scaffold(
        bottomBar = {
            HomeTopBar(navController = navController)
        },
    ) {
        HomeNavGraph(
            navController = navController,
            onSeeProfile = onSeeProfile
        )
    }

}






