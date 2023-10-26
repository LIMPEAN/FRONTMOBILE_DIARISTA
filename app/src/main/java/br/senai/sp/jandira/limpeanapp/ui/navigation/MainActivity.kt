package br.senai.sp.jandira.limpeanapp.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.AuthenticationRoute
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.LoginScreen
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.registerNavGraph
import br.senai.sp.jandira.limpeanapp.home.HomeScreen
import br.senai.sp.jandira.limpeanapp.home.HomeViewModel
import com.example.compose.LimpeanAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = AuthenticationRoute.Login.route
                ){
                    composable(AuthenticationRoute.Login.route){
                        LoginScreen(
                            onLogin = {
                                navHostController.navigate(NavigationRoute.Home.route){
                                    popUpTo(NavigationRoute.Home.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            onRegister = {
                                navHostController.navigate(AuthenticationRoute.Register.route)
                            }
                        )
                    }
                    registerNavGraph(navHostController)
                    composable(NavigationRoute.Home.route){
                        val vm = hiltViewModel<HomeViewModel>()
                        vm.findDiarist()
                        HomeScreen(diarist = vm.profileDiarist)
                    }
                }
            }
        }
    }
}

