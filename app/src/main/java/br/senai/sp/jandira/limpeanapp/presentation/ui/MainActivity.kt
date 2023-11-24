package br.senai.sp.jandira.limpeanapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.presentation.navigation.NavigationHost
import br.senai.sp.jandira.limpeanapp.presentation.navigation.NavigationRoute
import com.example.compose.LimpeanAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }

        }
        setContent {
            LimpeanAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    var startDestination = NavigationRoute.AUTHENTICATION
                    LaunchedEffect(viewModel.isLoading){
                        viewModel.isLogged.collect{isLogged ->
                            startDestination = if(isLogged){
                                NavigationRoute.HOME
                            } else {
                                NavigationRoute.AUTHENTICATION
                            }
                        }
                    }
                    NavigationHost(
                        startDestination = startDestination
                    )
                }
            }
        }
    }

    private fun getStartedDestination() : String {
        val isLogged = viewModel.isLogged.value
        return if(isLogged){
            NavigationRoute.HOME
        } else {
            NavigationRoute.AUTHENTICATION
        }
    }
}

