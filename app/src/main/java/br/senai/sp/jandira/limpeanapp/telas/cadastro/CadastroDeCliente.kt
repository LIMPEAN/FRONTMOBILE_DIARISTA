package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import br.senai.sp.jandira.limpeanapp.dados.modelos.Endereco
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.BotaoDeCadastro
import com.google.gson.Gson
import java.time.LocalDate

@SuppressLint("SuspiciousIndentation")
@Composable
fun CadastroDeCliente(
    status : String?,
    cadastrarDiaristaFake : (DiaristaApi) -> Unit
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
            val diaristaFake = gson.fromJson(diaristaFakeEmJson, DiaristaApi::class.java)
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
    Column {

        TelaDeCadastro(titulo = "Cadastro Rápido") {
            CadastroDeCliente(status = viewModel.cadastroState.status, cadastrarDiaristaFake = {

            })

        }

    }

}
//private fun createUser() {
//
//    lifecycleScope.launch {
//
//        val body = JsonObject().apply {
//            addProperty("name", "Vinicius")
//            addProperty("job", "Desenvolvedor Web")
//        }
//
//        val result = apiService.createUser(body)
//
//        if (result.isSuccessful) {
//            Log.e("CREATEDATA", "${result.body()}")
//        } else {
//            Log.e("CREATEDATA", "${result.message()}")
//        }
//    }
//
//
//}
