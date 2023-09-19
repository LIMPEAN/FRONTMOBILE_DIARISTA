package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.md_theme_light_primary

@Composable
fun BotaoDeCadastro(
    nomeDaAcao : String,
    aoClicarNoBotao : ()-> Unit
) {
    Button(
        onClick = { aoClicarNoBotao() },
        modifier = Modifier
            .width(376.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(md_theme_light_primary)
    ) {
        Text(nomeDaAcao)
    }
}