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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.dados.modelos.Usuario
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.cadastro.IntegracaoDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeCasa
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeEndereco
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePerfil
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePessoa
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import br.senai.sp.jandira.limpeanapp.telas.login.TelaDeLogin
import com.example.compose.LimpeanAppTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                val gson = Gson()
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = "cadastro",
                ){
                    composable(route = "boas_vindas"){
                        TelaInicial(
                            navegarParaLogin = {
                                navController.navigate("login/$it")
                            },
                            navegarParaCadastro = {
                                val tipoDeUsuarioEmJson = gson.toJson(it)
                                navController.navigate("cadastro/${tipoDeUsuarioEmJson}" )
                            }
                        )
                    }
                    composable(route = "login/{tipoDeUsuario}"){
                        val tipoDeUsuarioEmJson = it.arguments!!.getString("tipoDeUsuario")
                        val tipoDeUsuario = gson.fromJson(tipoDeUsuarioEmJson, TipoDeUsuario::class.java)
                        TelaDeLogin(
                            tipoDeUsuario
                        ){

                        }
                    }
                    navigation(route = "cadastro/{tipoDeUsuario}", startDestination = "pessoa"){
                        val tipoDeUsuario = navController
                            .currentBackStackEntry?.arguments
                            ?.getString("tipoDeUsuario")?.let {
                                gson.fromJson(it, TipoDeUsuario::class.java)
                            }
                        val viewModel by viewModels<IntegracaoDeCadastro> {
                            IntegracaoDeCadastro.fazerIntegracaoFake(tipoDeUsuario!!)
                        }
                        val uiState = viewModel.cadastroState
                        composable("pessoa"){

                            TelaDeCadastro(titulo = "Cadastro de ${uiState.tipoDeUsuario.nomeEmPortugues}") {
                               FormularioDePessoa(
                                   salvarDados = {viewModel.alterarDadosDePessoa(it)})
                            }
                        }
                        composable("perfil"){
                            TelaDeCadastro(titulo = "Perfil") {

                            }
                        }
                        composable("endereco"){
                            TelaDeCadastro(titulo = "Endereco") {

                            }
                        }
                    }

                }


            }
        }
    }
}



