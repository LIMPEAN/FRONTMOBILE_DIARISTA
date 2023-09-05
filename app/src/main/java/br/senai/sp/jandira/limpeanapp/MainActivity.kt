package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.registration.person.RegisterPersonScreen
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserScreen
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserViewModel
import br.senai.sp.jandira.limpeanapp.utils.Navigation

import br.senai.sp.jandira.limpeanapp.utils.Screen
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

