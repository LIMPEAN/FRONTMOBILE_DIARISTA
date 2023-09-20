package br.senai.sp.jandira.limpeanapp.telas.login

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.dados.Logar
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeSenha
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_background
import com.example.compose.md_theme_light_onBackground
import com.example.compose.md_theme_light_onPrimary
import com.example.compose.md_theme_light_primary

@Composable
fun TelaDeLogin(
    aoClicarEmLogar: (Logar) -> Unit
) {

    var login by remember {
        mutableStateOf(Logar())
    }
    Column (
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = "Entrar", color = md_theme_light_primary, fontSize = 30.sp)
        Text(text = "Insira seu e-mail e senha")

        CaixaDeSenha(etiqueta = "Digite sua senha...", estado = login.senha, aoDigitar ={} )

        Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){

            val checkedState = remember { mutableStateOf(true) }

            Row (verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )

                Text(text = "Relembre-me", color = md_theme_light_primary, fontSize = 13.sp)
            }

            TextButton(
                onClick = { /*TODO*/ }) {
                Text("esqueceu a senha?")
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                aoClicarEmLogar(login)
            } , modifier = Modifier.width(340.dp)
        ) {
            Text(text = "Logar")
        }

        Spacer(modifier = Modifier.height(25.dp))

        TextComLinhasLogin(texto = "ou faça login com")

        Button(onClick = { /*TODO*/ }, Modifier.background(Color.White) //alterar
        ) {
            Text(text = "Logar com o Google")
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {

    LimpeanAppTheme {
        TelaDeLogin(aoClicarEmLogar = {"teste"})
    }
}