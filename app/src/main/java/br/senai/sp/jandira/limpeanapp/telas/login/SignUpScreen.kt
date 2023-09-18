package br.senai.sp.jandira.limpeanapp.telas.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.LimpeanAppTheme

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit
) {
    Column {
        Text(text = "Entrar no App")
        Button(onClick = onLoginClick) {
            Text(text = "Logar")
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    LimpeanAppTheme {
        SignUpScreen({})
    }
}