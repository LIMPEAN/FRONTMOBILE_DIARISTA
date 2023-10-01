package br.senai.sp.jandira.limpeanapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    starDestination : NavigationRoute
){
    NavHost(
        navController = navHostController,
        startDestination = starDestination.route
    ){
        navigation(startDestination = RegisterRoute.Person.route, route = NavigationRoute.Authentication.route){
            composable(RegisterRoute.Person.route){

            }
        }
    }
}