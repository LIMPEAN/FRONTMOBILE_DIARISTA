package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.regras.Gender
import br.senai.sp.jandira.limpeanapp.regras.Person
import com.example.compose.LimpeanAppTheme
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDeCadastro(
    titulo: String,
    conteudo: @Composable () -> Unit,
    navController: NavController,
    nomeDaAcaoDoBotao : String,
    aoClicarNoBotao : ()-> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(12.dp),
                title = { Text(text = titulo) },
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

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = aoClicarNoBotao) {
                    Text(nomeDaAcaoDoBotao)
                }
            }
        }
    )
}

@Preview
@Composable
fun SignInPersonScreenPreview() {
    var person = Person(
        "Felipe",
        Date(),
        Gender.values()[0],
        "",
        "")
    val navController = rememberNavController()
    LimpeanAppTheme {
        TelaDeCadastro(
            titulo = "Dados Pessoais",
            conteudo = { },
            nomeDaAcaoDoBotao = "Próximo",
            aoClicarNoBotao = {},
            navController = navController
        )
    }
}