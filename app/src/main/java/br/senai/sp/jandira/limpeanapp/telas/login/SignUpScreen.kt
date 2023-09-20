package br.senai.sp.jandira.limpeanapp.telas.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.telas.login.components.TextComLinhasLogin
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit
) {
    Column (modifier = Modifier.padding(20.dp) ,horizontalAlignment = Alignment.Start){

        Text(text = "Entrar", color = md_theme_light_primary)
        Text(text = "Insira seu e-mail e senha")



        formLogin()

        Row {

            val checkedState = remember { mutableStateOf(true) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )

            TextButton(
                onClick = { /*TODO*/ }) {
                Text("esqueceu a senha?")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = onLoginClick) {
            Text(text = "Log in")
        }

        Spacer(modifier = Modifier.height(25.dp))

        TextComLinhasLogin(texto = "ou faça login com")
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    LimpeanAppTheme {
        SignUpScreen({})
    }
}