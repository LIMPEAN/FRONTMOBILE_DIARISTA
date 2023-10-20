package br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.PasswordField
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.repository.AuthRepositoryImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.di.LoginModule
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components.TextComLinhasLogin
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components.InputTextEmail
import com.example.compose.md_theme_light_error



@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context){
        viewModel.authResult.collect{ result ->
            when(result){
                is AuthResult.Authorized -> {
                    onLogin()
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "Não autorizado!",
                        Toast.LENGTH_SHORT).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "Um erro inesperado aconteceu. Tente novamente.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )

}


@Composable
private fun LoginScreen(
    state : LoginState,
    onEvent: (LoginEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        TitleSection(title = stringResource(R.string.section_entrar), description = stringResource(R.string.section_entrar_description))

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputTextEmail(
                label = "Email",
                keyboardType = KeyboardType.Text,
                state = state.email,
                onTyping = {
                    onEvent(LoginEvent.EmailChanged(it))
                },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = state.emailError?: "",
                color = md_theme_light_error
            )
            PasswordField(
                etiqueta = "Digite sua senha...",
                estado = state.password,
                aoDigitar = {
                    onEvent(LoginEvent.PasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = state.passwordError?: "",
                color = md_theme_light_error
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                var checkedState = remember { mutableStateOf(false) }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )

                    Text(
                        text = "Relembrar",
                        color = md_theme_light_primary,
                        fontSize = 13.sp
                    )
                }

                TextButton(
                    onClick = { /*TODO*/ }) {
                    Text("Esqueceu a senha?")
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {



            Button(
                onClick = {
                    onEvent(LoginEvent.Login)
                },
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(text = "Logar como ${state.userType?: "Diarista"}")
            }


            TextComLinhasLogin(texto = "ou")

            OutlinedButton(onClick = { onEvent(LoginEvent.LoginWithGoogle)},
                modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.logo_google), contentDescription = "logo google")
                Text(text = "Entrar com Google")
            }
            if (state.isLoading){
                Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator()
                }
            }
            Column() {
                Text(
                    text = "Ainda não possui uma conta? Realize seu cadastro como",
                    fontSize = 12.sp)
                Row {
                    Text(fontSize = 12.sp , text = "Diarista", textDecoration = TextDecoration.Underline, modifier = Modifier.clickable {  })
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ou",
                        fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(fontSize = 12.sp , text = "Contratante", textDecoration = TextDecoration.Underline, modifier = Modifier.clickable {  })
                }
            }



        }



    }

}



@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {


    LimpeanAppTheme {
       LoginScreen(state = LoginState(), onEvent = {})
    }
}