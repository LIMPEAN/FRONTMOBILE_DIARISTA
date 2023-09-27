package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import br.senai.sp.jandira.limpeanapp.dados.repositorios.Status
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.BotaoDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.FormularioDePerfil
import com.example.compose.LimpeanAppTheme
import com.google.gson.Gson


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
        BotaoDeCadastro(nomeDaAcao = "Cadastrar") {
            val gson = Gson()
            val diaristaFake = gson.fromJson(diaristaFakeEmJson, UserApi::class.java)
            cadastrarDiaristaFake(diaristaFake)
//            Log.i("DiaristaFakeEmJson", diaristaFakeEmJson)

        }
    }
}

@Preview
@Composable
fun CadastroTeste() {
    val viewModel = viewModel<IntegracaoDeCadastro>(
        factory = IntegracaoDeCadastro.fazerIntegracaoComApi
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
            FormularioDePerfil()
        }
    }
}