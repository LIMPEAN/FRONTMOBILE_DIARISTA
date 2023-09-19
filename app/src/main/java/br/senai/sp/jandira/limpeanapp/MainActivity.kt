package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.limpeanapp.dados.User
import br.senai.sp.jandira.limpeanapp.dados.Usuario
import br.senai.sp.jandira.limpeanapp.regras.UserType
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeCasa
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeEndereco
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePerfil
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePessoa
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.Perfil
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import com.example.compose.LimpeanAppTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder

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
                    composable(route = "boas_vindas"){ TelaInicial(navController) }
                    composable(route = "cadastro_de_perfil/{tipoUsuario}"){

                        val tipoUsuarioEmJson = it.arguments!!.getString("tipoUsuario")
                        val tipoUsuario = gson.fromJson(tipoUsuarioEmJson, UserType::class.java)


                        TelaDeCadastro(
                            titulo = "Cadastro de  ${tipoUsuario.portugueseName} "
                        ){
                            FormularioDePerfil(){perfil ->
                                perfil.tipoUsuario = tipoUsuario
                                val perfilEmJson = gson.toJson(perfil)
                                Log.i("PERFIL-COMPLETO", perfilEmJson)
                                navController.navigate("cadastro_de_pessoa/${perfilEmJson}")
                            }
                        }
                    }
                    composable(route = "cadastro_de_pessoa/{perfil}"){
                        val perfilEmJson = it.arguments!!.getString("perfil")

                        val perfil = gson.fromJson(perfilEmJson, Perfil::class.java)
                        val tipoUsuario = perfil.tipoUsuario

                        Log.i("PERFIL-CADASTRO-PESSOA", perfil.toString())
                        TelaDeCadastro(
                            titulo = "Dados Pessoais",
                        ){
                            FormularioDePessoa()
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
                            conteudo = { FormularioDeEndereco() },
                        )


                    }

                }


            }
        }
    }
}


