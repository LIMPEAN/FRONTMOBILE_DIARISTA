package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.text.capitalize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.regras.UserType
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeCasa
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDeEndereco
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePerfil
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePessoa
import br.senai.sp.jandira.limpeanapp.telas.inicio.TelaInicial
import com.example.compose.LimpeanAppTheme
import com.google.gson.Gson
import java.util.Locale

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
                    composable(route = "cadastro_de_perfil/{tipoUsuario}"){
                        val tipoUsuarioEmJson = it.arguments!!.getString("tipoUsuario")
                        val gson = Gson()
                        val tipoUsuario = gson.fromJson(tipoUsuarioEmJson, UserType::class.java)
                        TelaDeCadastro(
                            titulo = "Cadastro de  ${tipoUsuario.portugueseName} ",
                            conteudo = { FormularioDePerfil() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Próxima"
                        ) {
                            navController.navigate("cadastro_de_pessoa/$tipoUsuario")
                        }
                    }
                    composable(route = "cadastro_de_pessoa/{tipoUsuario}"){
                        val tipoUsuarioEmJson = it.arguments!!.getString("tipoUsuario")
                        val gson = Gson()
                        val tipoUsuario = gson.fromJson(tipoUsuarioEmJson, UserType::class.java)
                        TelaDeCadastro(
                            titulo = "Dados Pessoais",
                            conteudo = { FormularioDePessoa() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Próxima"
                        ) {
                            if(tipoUsuario.portugueseName == "Diarista"){
                                navController.navigate("cadastro_de_endereco/teste")
                            } else {
                                navController.navigate("cadastro_de_casa")
                            }

                        }
                    }
                    composable(route = "cadastro_de_casa"){
                        val dadosDaCasa = "DadosDaCasa"
                        TelaDeCadastro(
                            titulo = "Adicione uma casa",
                            conteudo = { FormularioDeCasa() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Próxima"
                        ) {
                            navController.navigate("cadastro_de_endereco/$dadosDaCasa")
                        }
                    }
                    composable(route = "cadastro_de_endereco/{dadosDaCasa}"){
                        TelaDeCadastro(
                            titulo = "Cadastro do Endereço",
                            conteudo = { FormularioDeEndereco() },
                            navController = navController,
                            nomeDaAcaoDoBotao = "Finalizar"
                        ) {
                            
                        }
                    }
                }


            }
        }
    }
}

