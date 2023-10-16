package br.senai.sp.jandira.limpeanapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginScreen
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginViewModel
import br.senai.sp.jandira.limpeanapp.navigation.NavigationHost
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute
import com.example.compose.LimpeanAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navHostController = rememberNavController()
                NavigationHost(
                   navHostController = navHostController,
                   startDestination = NavigationRoute.Authentication
               )
            }
        }
    }
}

