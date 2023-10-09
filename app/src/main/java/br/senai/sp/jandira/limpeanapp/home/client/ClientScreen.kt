package br.senai.sp.jandira.limpeanapp.home.client

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
                                append("Tipo de solicitação")
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
                            .padding(25.dp)
                            .width(215.dp)
                    ) {
                        Text(
                            text = "Últimas Seviços,",
                            fontWeight = FontWeight.Medium,
                            fontSize = 28.sp
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .width(300.dp)
                                .height(215.dp),
                            colors = CardDefaults.cardColors(
                                Color.White
                            )
                        ) {
                            Column (
                                modifier = Modifier
                                    .width(348.dp)
                            ){
                                Row (){
                                    Text(text = "Maria Dolores")
                                    Text(text = "R\$400,00")
                                }
                                Text(text = "06600-025, Osasco, SP")
                                Row(modifier = Modifier.height(100.dp)){
                                    Button(onClick = { /*TODO*/ }) {

                                    }
                                    Button(onClick = { /*TODO*/ }) {
                                        
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Card(
                            modifier = Modifier
                                .width(300.dp)
                                .height(215.dp),
                            colors = CardDefaults.cardColors(Color(49, 71, 245))
                        ) {

                            Text(text = stringResource(id = R.string.closed_request))
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




