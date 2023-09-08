package br.senai.sp.jandira.limpeanapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(Screen.LoginScreen.route){
            LoginScreen(onCreateAccount = {}, onLogin = {})
        }
    
    }
}