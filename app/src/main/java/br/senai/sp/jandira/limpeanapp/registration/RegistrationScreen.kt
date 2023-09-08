package br.senai.sp.jandira.limpeanapp.registration

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun RegistrationScreen(
    nameUserType: String
) {
Log.i("recebendo", nameUserType)
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "object $nameUserType")

    }
}