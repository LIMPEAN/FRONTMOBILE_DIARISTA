package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.dados.Usuario
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
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
                    startDestination = "boas_vindas",
                ){
                    composable(route = "boas_vindas"){
                        TelaInicial(
                            navegarParaLogin = {
                                navController.navigate("login/$it")
                            },
                            navegarParaCadastro = {
                                val tipoDeUsuarioEmJson = gson.toJson(it)
                                navController.navigate("cadastro_de_pessoa/${tipoDeUsuarioEmJson}" )
                            }
                        )
                    }
                    composable(route = "cadastro_de_pessoa/{tipoUsuario}"){
                        val tipoUsuarioEmJson = it.arguments!!.getString("tipoUsuario")
                        val tipoUsuario = gson.fromJson(tipoUsuarioEmJson, TipoDeUsuario::class.java)
                       
                        TelaDeCadastro(
                            titulo = "Dados Pessoais",
                        ){
                            FormularioDePessoa(){pessoa ->
                                val usuario = Usuario(
                                    tipoUsuario = tipoUsuario,
                                    dadosDePessoa = pessoa
                                )
                                val usuarioEmJson = gson.toJson(usuario)
                                navController.navigate("cadastro_de_perfil/$usuarioEmJson")

                            }
                        }



                    }
                    composable(route = "cadastro_de_perfil/{usuario}"){
                        val usuarioEmJson = it.arguments!!.getString("usuario")
                        val usuario = gson.fromJson(usuarioEmJson, Usuario::class.java)
                        val tipoUsuario = usuario.tipoUsuario!!.nomeEmPortugues

                        TelaDeCadastro(
                            titulo = "Cadastro de  ${usuario.tipoUsuario?.nomeEmPortugues} "
                        ){
                            FormularioDePerfil(){perfil ->
                                usuario.biografia = perfil.biografia
                                usuario.email = perfil.email
                                usuario.senha = perfil.senha
                                usuario.fotoPerfil = perfil.fotoDePerfil

                                val usuarioEmJson = gson.toJson(usuario)

                                Log.i("USUARIO", usuarioEmJson)

                                navController.navigate("cadastro_de_endereco/$usuarioEmJson")
                            }
                        }
                    }
                    composable(route = "cadastro_de_casa/{dadosDeCliente}"){
                        val dadosDeCliente = it.arguments!!.getString("dadosDeCliente")
                        Log.i("DADOS-CLIENTE", dadosDeCliente.toString())
                        TelaDeCadastro(
                            titulo = "Adicione uma casa",
                            conteudo = { FormularioDeCasa() },
                        )
                            navController.navigate("cadastro_de_endereco/$it")

                    }
                    composable(route = "cadastro_de_endereco/{dadosDeUsuario}"){

                        TelaDeCadastro(
                            titulo = "Cadastro do Endereço ${it.arguments!!.getString("dadosDeUsuario")}",
                        ){
                            FormularioDeEndereco(){

                            }
                        }


                    }
                    composable(route = "login/{tipoDeUsuario}"){
                        val tipoDeUsuarioEmJson = it.arguments!!.getString("tipoDeUsuario")
                        val tipoDeUsuario = gson.fromJson(tipoDeUsuarioEmJson, TipoDeUsuario::class.java)
                        TelaDeLogin(
                            tipoDeUsuario
                        ){

                        }
                    }

                }


            }
        }
    }
}


