package br.senai.sp.jandira.limpeanapp.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.seed

@Composable
fun HomeScreen(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(seed)){

        Text(
            text = "Deixe sua casa mais limpa",
            color = Color.White,
            fontFamily = poopins,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )


    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}