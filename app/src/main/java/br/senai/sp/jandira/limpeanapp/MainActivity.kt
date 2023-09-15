package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.ui.signin.SignInScreen
import br.senai.sp.jandira.limpeanapp.ui.signin.components.PersonForm
import br.senai.sp.jandira.limpeanapp.ui.welcome.WelcomeScreen
import com.example.compose.LimpeanAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = "login",
                ){
                    composable(route = "boas_vindas/{tipoUsuario}"){ WelcomeScreen(navController) }
                    composable(route = "cadastro_de_pessoa/{tipoUsuario}"){
                        SignInScreen(
                            title = "Cadastro de Usuário",
                            content = { PersonForm()},
                            onClickButton = {},
                            nameActionButton =
                    )}
                    composable(route = "cadastro_de_usuario"){ PedidosScreen(navController) }
                    composable(route = "cadastro_de_casa"){
                        var nome = it.arguments!!.getString("nomeUsuario")
                        PerfilScreen(navController = navController, nome = nome!!)
                    }
                }


            }
        }
    }
}

