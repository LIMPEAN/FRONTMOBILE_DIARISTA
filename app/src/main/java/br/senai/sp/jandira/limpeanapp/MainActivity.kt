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
<<<<<<< HEAD
import br.senai.sp.jandira.limpeanapp.navigation.RegisterRoute
import br.senai.sp.jandira.limpeanapp.telas.AuthViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterAddressScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterProfileScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterRoute
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.PersonForm
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.AddressForm
import br.senai.sp.jandira.limpeanapp.telas.login.LoginScreen
=======
>>>>>>> a4ca33250a94d6a93f8151f3dfe27dc281529df4
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