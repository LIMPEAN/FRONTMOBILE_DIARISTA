package br.senai.sp.jandira.limpeanapp.telas.cadastro

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterRoute


@Composable
fun RegisterRoute(
    navHostController : NavHostController,
    startDestination : RegisterRoute
){
    NavHost(navController = navHostController, startDestination = startDestination.route){
        composable(RegisterRoute.Profile.route){}
        composable(RegisterRoute.Person.route){}
        composable(RegisterRoute.Address.route){}
    }
}
