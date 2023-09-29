package br.senai.sp.jandira.limpeanapp.telas.cadastro



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonForm(
    navController : NavController = rememberNavController()
) {


    var nameState by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Dados Pessoais",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
            )
        }
    ) {paddingValues ->

            OutlinedTextField(
                label = {
                    Text(text = "Nome")
                },
                modifier = Modifier.padding(paddingValues),
                value = nameState,
                onValueChange = {
                    nameState = it
                }
            )


    }
}

@Preview(showSystemUi = true)
@Composable
fun PersonFormPreview() {

    LimpeanAppTheme {
        PersonForm()
    }
}