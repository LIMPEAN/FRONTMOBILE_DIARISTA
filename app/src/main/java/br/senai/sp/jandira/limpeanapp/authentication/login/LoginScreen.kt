package br.senai.sp.jandira.limpeanapp.authentication.login

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.authentication.componentes.PasswordField
import br.senai.sp.jandira.limpeanapp.authentication.login.components.TextComLinhasLogin
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.authentication.AuthRepository
import br.senai.sp.jandira.limpeanapp.authentication.login.components.InputTextEmail
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.example.compose.md_theme_light_error



@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = LoginViewModel(AuthRepository(RetrofitFactory.getUserService()))
) {


    var state by remember {
        mutableStateOf("")
    }
    val uiState = viewModel.uiState
    val emailState = uiState.email
    val passwordState = uiState.password


    LaunchedEffect(uiState.logged){
        if(uiState.logged){
            onLogin()
        }

    }
    Column(

        modifier = Modifier.padding(20.dp).fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Entrar", color = md_theme_light_primary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = "Insira seu e-mail e senha 2")

        InputTextEmail(
            label = "Email",
            keyboardType = KeyboardType.Text,
            state = emailState.value,
            onTyping = { emailState.change(it)}
        )
        if(emailState.hasError){
            Text(
                text = emailState.errorMessage,
                color = md_theme_light_error
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        PasswordField(
            etiqueta = "Digite sua senha...",
            estado = passwordState.value,
            aoDigitar = {passwordState.change(it)},
        )
        if(passwordState.hasError){
            Text(
                text = passwordState.errorMessage,
                color = md_theme_light_error
            )
        }

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
            onClick = { viewModel.handle(
                email = emailState.value,
                password = passwordState.value) },
            modifier = Modifier.width(340.dp),
        ) {
            Text(text = "Logar")
        }
        if (uiState.isLoading){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }

        }
        if(uiState.message != null){
            Text(text = uiState.message)
        }

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