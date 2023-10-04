package br.senai.sp.jandira.limpeanapp.home.client

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(color = seed)
                    .padding(start = 55.dp, bottom = 70.dp)
                    .offset(x = 6.dp)
                ,
                verticalArrangement = Arrangement.Center
            ) {

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


        }
    ){
        Column(Modifier.fillMaxSize()) {

            Text(text = "tese")
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun ClientePreview() {
    ClienteViewDiarist()
}

