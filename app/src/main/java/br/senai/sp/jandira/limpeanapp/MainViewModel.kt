package br.senai.sp.jandira.limpeanapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.login.LoginScreen
import br.senai.sp.jandira.limpeanapp.login.LoginViewModel
import br.senai.sp.jandira.limpeanapp.login.UserType
import br.senai.sp.jandira.limpeanapp.registration.RegistrationScreen

import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserState
import br.senai.sp.jandira.limpeanapp.utils.Screen

class MainViewModel(
    private val loginViewModel: LoginViewModel = LoginViewModel()
) : ViewModel(){

    @Composable
    fun initNavigationScreens(){
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.LoginScreen.route
            ){
                composable(Screen.LoginScreen.route){
                    LoginScreen(
                        onCreateAccount = {
                            navController.navigate("form_screen/${loginViewModel.state.typeUser?.id}")
                        },
                        onLogin = {}
                    )
                }
                composable(Screen.RegistrationScreen.route){
                   val idUserType = it.arguments!!.getLong("idTypeUser")
                    Log.i("ID_USER", idUserType.toString())
                    RegistrationScreen(idUserType)
                }

            }
            showHello()

    }


    private fun showHello(){
        Log.i("HELLO", "Iniciado")
    }

}