package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePessoa
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import com.example.compose.LimpeanAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = "boas_vindas",
                ){
                    composable(route = "boas_vindas"){ TelaInicial(navController) }
                    composable(route = "cadastro_de_pessoa/{tipoUsuario}"){
                        TelaDeCadastro(
                            titulo = "Dados Pessoais",
                            conteudo = { FormularioDePessoa() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Próxima"
                        ) {
                            navController.navigate("cadastro_de_usuario")
                        }
                    }
                    composable(route = "cadastro_de_usuario"){
                        TelaDeCadastro(
                            titulo = "Cadastro de Usuário",
                            conteudo = { FormularioDePessoa() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Próxima"
                        ) {
                            navController.navigate("login")
                        }
                    }
                }


            }
        }
    }
}

