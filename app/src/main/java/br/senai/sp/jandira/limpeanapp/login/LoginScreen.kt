package br.senai.sp.jandira.limpeanapp.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.login.components.SectionButton
import br.senai.sp.jandira.limpeanapp.login.components.SelectTypeUser
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_primary
import br.senai.sp.jandira.limpeanapp.components.textComLinhas

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    onCreateAccount: (userType: String) -> Unit,
    onLogin: (userType: String) -> Unit,
) {

    val userTypes = InMemoryUserTypeRepository.getAll()
    var selected by remember {
        mutableStateOf(userTypes[0])
    }
    val heightModifiers = 40.dp
    Column(
        modifier = Modifier
            .background(md_theme_light_primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 32.dp)
        ) {
            Logo()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(heightModifiers))
            Text(
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
            SelectTypeUser(
                userTypes = userTypes,
                selected = selected,
                selectedChange = {
                    selected = it
                }
            )
            Row {
                Button(
                    onClick = {
                        onCreateAccount(selected.apiName)
                    }
                ) {
                    Text(text = "Cadastro")
                }
                Button(
                    onClick = {
                        onLogin(selected.apiName)
                    }
                ) {
                    Text(text = "Login")
                }
            }


        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {

    LimpeanAppTheme {
       LoginScreen(
           onCreateAccount = {
               Log.i("onCreated", it)
           },
           onLogin = { Log.i("onLogin", it)})
    }
}

@Composable
fun Logo() {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}
@Composable
fun textLine(){
    Card (shape = RoundedCornerShape(0.dp)){
        textComLinhas(texto = " Cadastre-se como " )
    }
}




