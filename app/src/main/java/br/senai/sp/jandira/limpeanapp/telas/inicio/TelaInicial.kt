package br.senai.sp.jandira.limpeanapp.telas.inicio

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.dados.UserTypesRepository
import br.senai.sp.jandira.limpeanapp.telas.inicio.components.SectionButton
import br.senai.sp.jandira.limpeanapp.telas.inicio.components.SelectUserType
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun TelaInicial(
    navController: NavController
) {
    val tiposDeUsuario = UserTypesRepository.getAll()
    var usuarioSelecionado by remember {
        mutableStateOf(tiposDeUsuario[0])
    }

    val heightModifiers = 28.dp


    Column(
        modifier = Modifier
            .background(md_theme_light_primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 32.dp),
        ) {
            Logo()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightModifiers)
            )
            Text(
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.cleaning_service_cuate_1),
                contentDescription = "Cleaning Service Home",
                Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightModifiers)
            )

            SelectUserType(
                userTypes = tiposDeUsuario,
                selectedUserType = usuarioSelecionado,
                onSelectedChange = { usuarioSelecionado = it }
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionButton(name = "Login") {
                    navController.navigate("login/$usuarioSelecionado")
                }
                Spacer(modifier = Modifier.height(12.dp))
                SectionButton(name = "Cadastro") {
                    val convertorDeJson = Gson()
                    val tipoDeUsuarioEmJson = convertorDeJson.toJson(usuarioSelecionado)
                    navController.navigate("cadastro_de_perfil/${tipoDeUsuarioEmJson}" )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TelaInicialPreview() {

    val navController = rememberNavController()
    LimpeanAppTheme {
        TelaInicial(
            navController = navController
        )
    }
}

@Composable
fun Logo() {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

