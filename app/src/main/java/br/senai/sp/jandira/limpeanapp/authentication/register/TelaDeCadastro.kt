package br.senai.sp.jandira.limpeanapp.authentication.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.authentication.register.profile.UserForm
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import br.senai.sp.jandira.limpeanapp.dados.repositorios.Status
import br.senai.sp.jandira.limpeanapp.authentication.componentes.Button
import com.google.gson.Gson


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




@SuppressLint("SuspiciousIndentation")
@Composable
fun CadastroDeCliente(
    status : String?,
    cadastrarDiaristaFake : (UserApi) -> Unit
) {

    val diaristaFakeEmJson = "{   \n" +
            "    \"typeUser\": \"diarist\",\n" +
            "    \"email\": \"paulo@gmail.com\",\n" +
            "    \"password\": \"135796\",\n" +
            "    \"nameUser\" : \"Felipe Florencio\",\n" +
            "    \"photoUser\" : \"https://fotoUsuario.png\",\n" +
            "    \"phone\": \"959601631\",\n" +
            "    \"ddd\": \"11\",\n" +
            "    \"birthDate\": \"2006-12-23\",\n" +
            "    \"idGender\": 2,\n" +
            "    \"cpf\": \"449.688.110-12\",\n" +
            "    \"biography\": \"Biografia. OBS: Pode ser null\",\n" +
            "    \"averagePrice\": \"2.00\",\n" +
            "            \"address\" : {\n" +
            "                 \"state\": 4,\n" +
            "                 \"city\": \"Cidade\",\n" +
            "                 \"cep\" : \"06720250\",\n" +
            "                 \"publicPlace\" : \"Rua da flores\",\n" +
            "                 \"complement\": \"Complemento. OBS: Pode ser null\",\n" +
            "                 \"district\": \"Bairroo\",\n" +
            "                 \"houseNumber\": \"203\"\n" +
            "                }\n" +
            "    \n" +
            "}"


    if(status != null){
        Text(text = status)
    }
    TelaDeCadastro(titulo = "Cadastro Rapido") {
        Button("Cadastrar", onClick = {val gson = Gson()
            val diaristaFake = gson.fromJson(diaristaFakeEmJson, UserApi::class.java)
            cadastrarDiaristaFake(diaristaFake)
            //Log.i("DiaristaFakeEmJson", diaristaFakeEmJson)
        })

    }
}

@Preview
@Composable
fun CadastroTeste() {
    val viewModel = viewModel<SignInViewModel>(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
    val uiState = viewModel.cadastroState
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.status) {
        val mensagem = when (uiState.status) {
            Status.LOADING -> "Carregando..."
            Status.SUCCESS -> "Cadastro bem-sucedido"
            Status.ERROR -> "Erro no cadastro"
            else -> ""
        }

        if (mensagem.isNotEmpty()) {
            Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
        }
    }


    Column {
        TelaDeCadastro(titulo = "Cadastro Rápido") {
            CadastroDeCliente(status = viewModel.cadastroState.status) { userData ->
                viewModel.cadastrarUsuario(userData)
            }
        }
    }
}

@Preview
@Composable
fun CadastroTeste1() {
    LimpeanAppTheme {
        TelaDeCadastro(titulo = "Crie seu Perfil") {
            UserForm()
        }
    }
}

