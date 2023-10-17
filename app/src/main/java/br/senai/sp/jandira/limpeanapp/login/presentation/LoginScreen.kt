package br.senai.sp.jandira.limpeanapp.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.PasswordField
import br.senai.sp.jandira.limpeanapp.login.data.api.AuthResult
import br.senai.sp.jandira.limpeanapp.login.presentation.components.TextComLinhasLogin
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import br.senai.sp.jandira.limpeanapp.login.presentation.components.InputTextEmail
import com.example.compose.md_theme_light_error



@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
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
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Entrar", color = md_theme_light_primary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = "Insira seu e-mail e senha 2")

        InputTextEmail(
            label = "Email",
            keyboardType = KeyboardType.Text,
            state = state.email,
            onTyping = {
                viewModel.onEvent(LoginEvent.EmailChanged(it))
            }
        )

        Text(
            text = state.emailError?: "",
            color = md_theme_light_error
        )


        Spacer(modifier = Modifier.height(25.dp))

        PasswordField(
            etiqueta = "Digite sua senha...",
            estado = state.password,
            aoDigitar = {
                viewModel.onEvent(LoginEvent.PasswordChanged(it))
            },
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
                    text = "Me lembre",
                    color = md_theme_light_primary,
                    fontSize = 13.sp
                )
            }

            TextButton(
                onClick = { /*TODO*/ }) {
                Text("esqueceu a senha?")
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                 viewModel.onEvent(LoginEvent.Login)
            },
            modifier = Modifier.width(340.dp),
        ) {
            Text(text = "Logar")
        }
        if (state.isLoading){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }

        }

        Text(text = state.message?: "")


        Spacer(modifier = Modifier.height(25.dp))

        TextComLinhasLogin(texto = "ou faça login com")

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedButton(onClick = { /*TODO*/ },
            modifier = Modifier.width(340.dp)) {
            Image(painter = painterResource(id = R.drawable.logo_google), contentDescription = "logo google")
            Text(text = "Logar com o google")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Ainda não possui uma conta? Realize seu cadastro como",
            fontSize = 12.sp)

        Row (verticalAlignment = Alignment.CenterVertically){

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Diarista", textDecoration = TextDecoration.Underline )
            }

            Text(text = "ou",
                fontSize = 14.sp)

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Contratante", textDecoration = TextDecoration.Underline)
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {

    LimpeanAppTheme {
       LoginScreen(
           onLogin = {
               Log.i("ONLOGIN", "presente")
           }
       )
    }
}