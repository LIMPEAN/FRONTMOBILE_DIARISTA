package br.senai.sp.jandira.limpeanapp.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    diarist: String,
) {

//    Scaffold {
//    }

    Box (contentAlignment = Alignment.Center){
        Text(text = "Oi diarist: $diarist")

        Log.i("TESTE","variavel : $diarist")
    }
}

@Preview
@Composable
fun HomeScreenPrev() {

}