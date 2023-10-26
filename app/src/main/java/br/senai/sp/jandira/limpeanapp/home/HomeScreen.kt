package br.senai.sp.jandira.limpeanapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    diarist: String,
) {

    Box (contentAlignment = Alignment.Center){
        Text(text = "Oi diarist: $diarist")
    }
}

@Preview
@Composable
fun HomeScreenPrev() {

}