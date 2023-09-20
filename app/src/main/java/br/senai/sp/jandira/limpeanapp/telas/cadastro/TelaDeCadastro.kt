package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.regras.Genero
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.BotaoDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePerfil
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePessoa
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onPrimary
import com.example.compose.md_theme_light_onSecondary
import com.example.compose.md_theme_light_primary
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDeCadastro(
    titulo: String,
    conteudo: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(12.dp),
                title = { Text(text = titulo,
                    fontSize = 32.sp,
                    fontWeight = FontWeight(600),
                    color = md_theme_light_primary,
                    textAlign = TextAlign.Center,
                ) },
                colors = TopAppBarDefaults.mediumTopAppBarColors()// Customize the top app bar color
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                conteudo() // PersonForm(), UserForm(), AdreesForm(), HomeForm()

                Spacer(modifier = Modifier.height(150.dp))


            }
        }
    )
}

@Preview
@Composable
fun SignInPersonScreenPreview() {
    var person = Pessoa(
        "Felipe",
        Date(),
        Genero.values()[0],
        "")
    val navController = rememberNavController()
    LimpeanAppTheme {
        TelaDeCadastro(
            titulo = "Dados Pessoais",
        ){
            FormularioDePerfil(){

            }
        }
    }
}