package br.senai.sp.jandira.limpeanapp.home.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.seed

@Composable
fun TypeOfRequest(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),

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
                                append("Loira")
                            }
                        }
                    },
                )
        }

            }




    ) {


    }
}