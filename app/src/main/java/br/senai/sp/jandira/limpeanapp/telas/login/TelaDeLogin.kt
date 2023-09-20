package br.senai.sp.jandira.limpeanapp.telas.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.dados.Logar
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import com.example.compose.LimpeanAppTheme

@Composable
fun TelaDeLogin(
    tipoDeUsuario : TipoDeUsuario,
    aoClicarEmLogar: (Logar) -> Unit
) {

    var login by remember {
        mutableStateOf(Logar())
    }
    Column {
        Text(text = "Logar como ${tipoDeUsuario.nomeEmPortugues}")
        Text(text = "Entrar no App")
        Button(
            onClick = {
                aoClicarEmLogar(login)
            }
        ) {
            Text(text = "Logar")
        }
    }
    
}

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