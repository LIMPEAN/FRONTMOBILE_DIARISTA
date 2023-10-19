package br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun formLogin(){

    var emailState by remember{
        mutableStateOf("")
    }

    var senhaState by remember{
        mutableStateOf("")
    }
        Column {
            inputText(
                label = "email",
                keyboardType = KeyboardType.Text,
                state = emailState,
                onTyping = {
                    emailState = it
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            inputText(
                label = "senha",
                keyboardType = KeyboardType.Password,
                state = senhaState,
                onTyping = {
                    senhaState = it
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
        }
}


@Preview(showBackground = true)
@Composable
fun FormPreview() {
    formLogin()
}
