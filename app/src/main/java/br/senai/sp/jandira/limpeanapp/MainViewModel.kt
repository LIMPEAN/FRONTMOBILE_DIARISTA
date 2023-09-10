package br.senai.sp.jandira.limpeanapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.login.LoginScreen
import br.senai.sp.jandira.limpeanapp.login.LoginViewModel
import br.senai.sp.jandira.limpeanapp.registration.RegistrationScreen
import br.senai.sp.jandira.limpeanapp.registration.RegistrationState
import br.senai.sp.jandira.limpeanapp.registration.RegistrationViewModel

import br.senai.sp.jandira.limpeanapp.utils.Screen

class MainViewModel(
    private val loginViewModel: LoginViewModel = LoginViewModel()
) : ViewModel(){

    @Composable
    fun initNavigationScreens() {
        val navController = rememberNavController()
        val viewModel = viewModel<RegistrationViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen.route
        ) {
            composable(Screen.LoginScreen.route) { route ->
                LoginScreen(
                    onCreateAccount = { userType ->
                        val destinationRoute = "form_screen/$userType"
                        navController.navigate(route = destinationRoute)
                    },
                    onLogin = { Log.i("onLogin", it) }
                )
            }
            composable(Screen.RegistrationScreen.route) { route ->
                val userType = route.arguments?.getString("nameUserType") ?: ""
                RegistrationScreen(
                    state = viewModel.registrationState.value,
                    userType = userType,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }


    private fun showHello(){
        Log.i("HELLO", "Iniciado")
    }

}