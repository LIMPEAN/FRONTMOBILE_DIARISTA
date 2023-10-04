package br.senai.sp.jandira.limpeanapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.navigation.NavigationHost
import br.senai.sp.jandira.limpeanapp.navigation.NavigationRoute

import com.example.compose.LimpeanAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navHostController = rememberNavController()
                NavigationHost(
                    navHostController = navHostController,
                    startDestination = getStartDestination()
                )
                }
            }
        }
    }

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

fun getStartDestination(): NavigationRoute{
    return NavigationRoute.Authentication
}