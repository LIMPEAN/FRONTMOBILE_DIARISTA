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
import br.senai.sp.jandira.limpeanapp.registration.RegistrationViewModel
import br.senai.sp.jandira.limpeanapp.registration.person.RegisterPersonEvent
import br.senai.sp.jandira.limpeanapp.registration.person.RegisterPersonScreen


import br.senai.sp.jandira.limpeanapp.utils.Screen

class MainViewModel(
    private val loginViewModel: LoginViewModel = LoginViewModel()
) : ViewModel(){

    @Composable
    fun initNavigationScreens() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen.route
        ) {
            composable(Screen.LoginScreen.route) {
                LoginScreen(
                    onCreateAccount = { userType ->
                        val viewModel = viewModel<RegistrationViewModel>()
                        navController.navigate(Screen.RegisterPersonScreen.route){
                            RegisterPersonScreen(userType = userType, state = , onEvent = )
                        }
                    },
                    onLogin = { userType ->
                        // Handle login
                        Log.i("onLogin", userType.toString())
                    }
                )
            }
            composable(Screen.RegisterPersonScreen.route) { route ->
                val userType = route.arguments?.getString("nameUserType") ?: ""
                RegisterPersonScreen(
                    state = viewModel,
                    userType = userType,
                    onEvent = { event ->
                        when (event) {
                            is RegisterPersonEvent.SubmitClicked -> {
                                // Determine which registration screen to navigate to based on user type
                                val nextScreen = if (userType == "Diarista") {
                                    Screen.RegisterAdressScreen
                                } else {
                                    Screen.RegisterUserScreen
                                }
                                navController.navigate(nextScreen.route)
                            }
                        }
                    }
                )
            }

        }
    }


    private fun showHello(){
        Log.i("HELLO", "Iniciado")
    }

}