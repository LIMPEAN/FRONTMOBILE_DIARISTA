package br.senai.sp.jandira.limpeanapp.telas.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.telas.login.components.TextComLinhasLogin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
//import br.senai.sp.jandira.limpeanapp.dados.Logar
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto

import androidx.compose.ui.tooling.preview.Preview

import br.senai.sp.jandira.limpeanapp.dados.modelos.Logar
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.componentes.inputText
import br.senai.sp.jandira.limpeanapp.telas.login.components.inputTextEmail
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_background
import com.example.compose.md_theme_light_onBackground
import com.example.compose.md_theme_light_onPrimary
import com.example.compose.md_theme_light_primary
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TelaDeLogin(
    tipoDeUsuario : TipoDeUsuario,
    aoClicarEmLogar: (Logar) -> Unit
) {

    var login by remember {
        mutableStateOf(Logar())
    }

    var state by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = "Entrar", color = md_theme_light_primary, fontSize = 30.sp)
        Text(text = "Insira seu e-mail e senha")

        inputTextEmail(
            label = "Email",
            keyboardType = KeyboardType.Text,
            state = state,
            onTyping = { state = it }
        )

        Spacer(modifier = Modifier.height(25.dp))

//        CaixaDeSenha(
//            etiqueta = "Digite sua senha...",
//            estado = login.senha,
//            aoDigitar = {}
//        )

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
                    text = "Relembre-me",
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
            onClick = { /*TODO*/ },
            modifier = Modifier.width(340.dp),
        ) {
            Text(text = "Logar")
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
@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showSystemUi = true)
@Composable
fun TelaDeLoginPreview() {
    LimpeanAppTheme {
        val tipoDeUsuario = TipoDeUsuario.pegaCliente()

        TelaDeLogin(
            tipoDeUsuario = tipoDeUsuario
        ){

        }

    }
}