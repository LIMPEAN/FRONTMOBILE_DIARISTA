package br.senai.sp.jandira.limpeanapp.home.client

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.authentication.componentes.Button
import com.example.compose.md_theme_light_primary
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientViewDiarist(){

Scaffold(
    modifier = Modifier.fillMaxSize()
    , topBar = {
         Box (
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight(0.13f)
                 .background(seed)
             ,
             contentAlignment = Alignment.Center
         ){
             Text(
                 buildAnnotatedString {
                     withStyle(style = ParagraphStyle()) {
                         withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                             append("Bem vinda de volta,\n")
                         }
                         withStyle(
                             style = SpanStyle(
                                 fontWeight = FontWeight.Bold,
                                 color = Color.White,
                                 fontSize = 28.sp
                             )
                         ) {
                             append("Leticia Wandinha")
                         }
                     }
                 },
             )

         }
    },
    bottomBar = {

    }
) {
    Box(
        modifier = Modifier.padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ola")
        }

    }
}


}

@Preview(showSystemUi = true)
@Composable
fun ClientePreview() {
    ClientViewDiarist()
}

