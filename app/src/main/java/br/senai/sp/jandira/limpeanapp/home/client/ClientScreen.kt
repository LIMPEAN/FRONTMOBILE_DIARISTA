package br.senai.sp.jandira.limpeanapp.home.client

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.PersonForm
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClienteViewDiarist(){

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.13f)
                    .background(color = seed)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bem vinda de volta,",
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

        }
    ){}


}

@Preview(showSystemUi = true)
@Composable
fun ClientePreview() {
    ClienteViewDiarist()
}

