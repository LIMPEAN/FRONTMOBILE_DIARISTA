package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi
import br.senai.sp.jandira.limpeanapp.telas.componentes.Button
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.google.gson.Gson


@Composable
fun RegisterScreen(
    navController : NavController = rememberNavController(),
    title : String,
    form : @Composable (PaddingValues) -> Unit,
    nameButton : String,
    onClick : () -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    color = md_theme_light_primary
                )
            }

        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 14.dp,
                            end = 16.dp,
                            bottom = 14.dp
                        )

                    ,
                    name = nameButton,
                    onClick = onClick
                )
            }


        }
    ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               form(paddingValues)
            }

        }

    }
}



//
//
////@Composable
////fun CadastroDeCliente(
////    status : String?,
////    cadastrarDiaristaFake : (UserApi) -> Unit
////) {
//
//    val diaristaFakeEmJson = "{   \n" +
//            "    \"typeUser\": \"diarist\",\n" +
//            "    \"email\": \"paulo@gmail.com\",\n" +
//            "    \"password\": \"135796\",\n" +
//            "    \"nameUser\" : \"Felipe Florencio\",\n" +
//            "    \"photoUser\" : \"https://fotoUsuario.png\",\n" +
//            "    \"phone\": \"959601631\",\n" +
//            "    \"ddd\": \"11\",\n" +
//            "    \"birthDate\": \"2006-12-23\",\n" +
//            "    \"idGender\": 2,\n" +
//            "    \"cpf\": \"449.688.110-12\",\n" +
//            "    \"biography\": \"Biografia. OBS: Pode ser null\",\n" +
//            "    \"averagePrice\": \"2.00\",\n" +
//            "            \"address\" : {\n" +
//            "                 \"state\": 4,\n" +
//            "                 \"city\": \"Cidade\",\n" +
//            "                 \"cep\" : \"06720250\",\n" +
//            "                 \"publicPlace\" : \"Rua da flores\",\n" +
//            "                 \"complement\": \"Complemento. OBS: Pode ser null\",\n" +
//            "                 \"district\": \"Bairroo\",\n" +
//            "                 \"houseNumber\": \"203\"\n" +
//            "                }\n" +
//            "    \n" +
//            "}"
//
//
//    if(status != null){
//        Text(text = status)
//    }
//    val gson = Gson()
//    RegisterScreen(
//        navController = rememberNavController(),
//        title = "Cadastro Rapido",
//        form = {
//            Text(modifier = Modifier.padding(it),text = "Formulario")
//        },
//        nameButton = "Cadastrar",
//        onClick = {
//            val diaristaFake = gson.fromJson(diaristaFakeEmJson, UserApi::class.java)
//            cadastrarDiaristaFake(diaristaFake)
//            //Log.i("DiaristaFakeEmJson", diaristaFakeEmJson)
//        }
//    )
//}


@Preview
@Composable
fun RegisterScreenDefaultPreview() {
    LimpeanAppTheme {
        RegisterScreen(
            navController = rememberNavController(),
            title = "Default",
            form = {
                Text(modifier = Modifier.padding(it),text = "Form")
            },
            nameButton = "Default Action",
            onClick = {}
        )
    }
}



