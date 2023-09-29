package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.cadastro.IntegracaoDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeEndereco
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import br.senai.sp.jandira.limpeanapp.telas.login.TelaDeLogin
import com.example.compose.LimpeanAppTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = "boas-vindas"
                ){
                    composable(route = "boas-vindas"){
                        val gson = Gson()
                        TelaInicial(
                            navegarParaLogin = {
                                navController.navigate("login/${gson.toJson(it)}"){
                                }
                            },
                            navegarParaCadastro = {
                                navController.navigate(
                                    "cadastro/${gson.toJson(it)}"
                                )
                            }
                        )
                    }
                    composable(route = "login/{tipoDeUsuario}"){
                        val gson = Gson()
                        val tipoDeUsuarioEmJson = it.arguments!!.getString("tipoDeUsuario")
                        val tipoDeUsuario = gson.fromJson(tipoDeUsuarioEmJson, TipoDeUsuario::class.java)

                    }
                    navigation(route = "cadastro/{tipoUsuario}", startDestination = "pessoa",
                        arguments = listOf(navArgument("tipoUsuario"){ type = NavType.StringType})){

                        val viewModel by viewModels<IntegracaoDeCadastro> {
                            IntegracaoDeCadastro.fazerIntegracaoComApi
                        }

                        composable("pessoa"){
                            val gson = Gson()
                            val tipoDeUsuario = it.arguments!!.getString("tipoUsuario").let {tipoDeUsuarioEmJson ->
                                gson.fromJson(tipoDeUsuarioEmJson, TipoDeUsuario::class.java)
                            }
                            viewModel.alterarTipoDeUsuario(tipoDeUsuario)

                            val uiState = viewModel.cadastroState

                            TelaDeCadastro(titulo = "Cadastro de ${uiState.tipoDeUsuario!!.nomeEmPortugues}") {
//                                FormularioDePessoa(){novaPessoa ->
//                                    navController.navigate("perfil")
//                                }
                            }
                        }
                        composable("perfil"){
                            TelaDeCadastro(titulo = "Perfil") {
//                                FormularioDePerfil(
//                                    tipoDeUsuario = viewModel.cadastroState.tipoDeUsuario!!,
//                                    salvarPerfil = {
//                                        navController.navigate("Endereco")
//                                    }
//                                )
                            }
                        }
                        composable("endereco"){
                            TelaDeCadastro(titulo = "Endereco") {
                                FormularioDeEndereco(){
                                    Log.i("USUARIO COMPLETO", viewModel.cadastroState.toString())
                                }
                            }
                        }
                    }

                }


            }
        }
    }
}