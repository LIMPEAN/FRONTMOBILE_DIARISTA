package br.senai.sp.jandira.limpeanapp.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.register.RegisterPersonalScreen
import br.senai.sp.jandira.limpeanapp.register.RegisterUserScreen
import br.senai.sp.jandira.limpeanapp.register.RegistrationViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val registrationViewModel = viewModel<RegistrationViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.RegisterUserScreen.route
    ){
       composable(Screen.RegisterUserScreen.route){
           RegisterUserScreen(
               navController = navController,
               viewModel = registrationViewModel
           )
       }
       composable(Screen.RegisterPersonalScreen.route){
           RegisterPersonalScreen(navController = navController, registrationViewModel)
       }

    }
}
