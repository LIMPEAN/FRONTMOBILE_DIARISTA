package br.senai.sp.jandira.limpeanapp.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.AuthenticationRoute
import br.senai.sp.jandira.limpeanapp.login.presentation.LoginScreen
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
                            }
                        )
                    }
                    composable(NavigationRoute.Home.route){
                        Box(contentAlignment = Alignment.Center) {
                            Text("Home Screen")
                        }
                    }
                }
            }
        }
    }
}

