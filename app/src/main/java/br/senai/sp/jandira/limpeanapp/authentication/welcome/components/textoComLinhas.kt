package br.senai.sp.jandira.limpeanapp.authentication.welcome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme

@Composable
fun textComLinhas(texto: String){
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(Color(49, 71, 245)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .width(100.dp)
                .height(3.dp)
        ){}
        Text(color = Color.White, text = texto)
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .width(100.dp)
                .height(3.dp)
        ){}
    }
}

@Preview
@Composable
fun TextoComLinhasPreview() {
    LimpeanAppTheme {
        Column {
            textComLinhas(texto = "Teste")
            textComLinhas(texto = "Teste")
            textComLinhas(texto = "Teste")
            textComLinhas(texto = "Teste")

        }

    }
}