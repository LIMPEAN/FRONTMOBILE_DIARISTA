package br.senai.sp.jandira.limpeanapp.ui.features.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun YourProfile() {
    Box (Modifier.fillMaxSize()){
        Text(text = "Here your profile")
    }
}
