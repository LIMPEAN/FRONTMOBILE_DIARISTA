package br.senai.sp.jandira.limpeanapp.home.client

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.authentication.componentes.MyButton
import com.example.compose.LimpeanAppTheme
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientViewDiarist() {


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .padding(start = 24.dp, bottom = 85.dp)
                    .offset(x = 6.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    buildAnnotatedString {
                        withStyle(style = ParagraphStyle()) {
                            withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                                append("criação \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                            ) {
                                append("Tânia Oliveira")
                            }
                        }
                    }
                )

            }
        },
        bottomBar = {
            Text(text = "footer")
        },

        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = seed), verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White),

                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                    ) {
                        Text(
                            text = "Últimas Seviços,",
                            fontWeight = FontWeight.Medium,
                            fontSize = 28.sp
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item() {
                            Card(
                                modifier = Modifier
                                    .width(350.dp)
                                    .height(215.dp),
                                colors = CardDefaults.cardColors(
                                    Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row(

                                    ) {
                                        Text(
                                            text = "Maria Dolores",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF393939),
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.width(100.dp))
                                        Text(
                                            text = "R\$400,00",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF3147F5)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "06600-025, Osasco, SP")
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Row(

                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Button(
                                            modifier = Modifier
                                                .width(145.dp)
                                                .height(40.dp),
                                            onClick = { /*TODO*/ }
                                        ) {
                                            Text(text = "Inicia")
                                        }
                                        Spacer(modifier = Modifier.width(15.dp))
                                        Button(
                                            modifier = Modifier
                                                .width(150.dp)
                                                .height(40.dp),
                                            shape = RoundedCornerShape(size = 12.dp),
                                            border = BorderStroke(1.dp, Color.Red),
                                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),

                                            onClick = { /*TODO*/ }
                                        ) {
                                            Text(text = "Cancelar")
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(50.dp))
                        }

                    }

                }
            }
        }

    )


}

@Preview(showSystemUi = true)
@Composable
fun ClientePreview() {
    LimpeanAppTheme {
        ClientViewDiarist()
    }
}




