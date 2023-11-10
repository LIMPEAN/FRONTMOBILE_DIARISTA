package br.senai.sp.jandira.limpeanapp.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme

@Composable
fun HomeTopBar(
    modifier : Modifier = Modifier,
    titleSmall : String,
    titleLarge : String
){
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            )) {
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = titleSmall,
            color = Color.White,
            fontFamily = poopins
        )
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = titleLarge,
            color = Color.White,
            fontFamily = poopins
        )
    }
}
@Preview
@Composable
fun HomeAppBarPreview() {
    LimpeanAppTheme {
        HomeTopBar(
            titleSmall = "Bem Vindo",
            titleLarge = "Felipe"
        )
    }
}