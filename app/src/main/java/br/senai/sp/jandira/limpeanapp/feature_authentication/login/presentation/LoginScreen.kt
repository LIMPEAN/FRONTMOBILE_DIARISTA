package br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.PasswordField
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.AuthResult
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components.TextComLinhasLogin
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components.InputTextEmail
import com.example.compose.md_theme_light_error



@Composable
fun LoginScreen(
    onRegister: () -> Unit,
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

                else -> {}
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onRegister = onRegister
    )

}


@Composable
private fun LoginScreen(
    state : LoginState,
    onEvent: (LoginEvent) -> Unit,
    onRegister : ()-> Unit
) {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        TitleSection(title = stringResource(R.string.login), description = stringResource(R.string.login_description))

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
                labelText = "Digite sua senha...",
                state = state.password,
                onValueChange = {
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
                horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
            ) {

                var checkedState = remember { mutableStateOf(false) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
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
                Text(
                    fontSize = 13.sp ,
                    text = "Esqueceu a senha?",
                    color = md_theme_light_primary
                )


            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            Button(
                onClick = {
                    onEvent(LoginEvent.Login)
                },
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(text = "Entrar")
            }
            TextComLinhasLogin(texto = "ou")
            OutlinedButton(
                onClick = {
                    onEvent(LoginEvent.LoginWithGoogle)
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Image(painter = painterResource(id = R.drawable.logo_google), contentDescription = "logo google")
                Text(text = "Entrar com Google")
            }
            if (state.isLoading){
                Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator()
                }
            }




        }
        Row() {
            Text(
                text = stringResource(R.string.question_have_account),
                fontSize = 12.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontSize = 12.sp ,
                text = stringResource(R.string.login_go_to_register),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        onRegister()
                    }
            )

        }


    }

}



@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LimpeanAppTheme {
       LoginScreen(state = LoginState(), onEvent = {}){}
    }

}