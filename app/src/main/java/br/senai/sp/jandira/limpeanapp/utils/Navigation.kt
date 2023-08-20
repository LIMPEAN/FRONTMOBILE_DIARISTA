package br.senai.sp.jandira.limpeanapp.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.registration.person.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserScreen
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val registrationUserViewModel = viewModel<RegistrationUserViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.RegisterUserScreen.route
    ){
       composable(Screen.RegisterUserScreen.route){
           RegistrationUserScreen(
               navController = navController,
               viewModel = registrationUserViewModel
           )
       }
       composable(Screen.RegisterPersonalScreen.route){
           RegisterPersonScreen(
               userState = registrationUserViewModel.state
           )
       }

    }
}
