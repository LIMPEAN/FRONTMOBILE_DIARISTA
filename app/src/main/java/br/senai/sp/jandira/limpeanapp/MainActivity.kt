package br.senai.sp.jandira.limpeanapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.register.RegisterPersonalScreen
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterPersonalScreen()
                }
            }
        }
    }
}

@Composable
fun CadastroDiaristaScreen() {
    Text(
        text = "Hello diarista!",
        fontWeight = FontWeight.Normal,
        fontFamily = poopins
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CadastroDiaristaScreenPreview() {
    LimpeanAppTheme {
        CadastroDiaristaScreen()
    }
}
