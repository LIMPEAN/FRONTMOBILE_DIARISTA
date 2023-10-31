package br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning


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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CleanScreen(
    onCreateService: () -> Unit
) {

    //fonte
    val customFontFamily = FontFamily(
        Font(R.font.poppins_regular)
    )

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
                            withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp, fontFamily = customFontFamily)) {
                                append("Bem vindo de volta, \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Leslie Almeida")
                            }
                        }
                    }
                )

            }
        },
        bottomBar = {
            Text(text = "navbar")
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                onCreateService()
//            }) {
//                Text(text = "Add")
//            }
//        },



        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = seed), verticalArrangement = Arrangement.Bottom
            ) {

                Column(modifier = Modifier.fillMaxSize().background(seed)) {


                    Column(modifier = Modifier.fillMaxSize()) {


                        Spacer(modifier = Modifier.height(100.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxSize(),
                            colors = CardDefaults.cardColors(Color.White),

                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))


                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Suas faxinas",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 26.sp,
                                    fontFamily = customFontFamily
                                )
                                Row(modifier = Modifier.height(50.dp)) {
                                    OutlinedTextField(value = "", onValueChange = {}, leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_search_24),
                                            contentDescription = "icone de pesquisa"
                                        )
                                    }, shape = RoundedCornerShape(12.dp))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Card(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(50.dp), colors = CardDefaults.cardColors(seed)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.FilterAlt,
                                                contentDescription = "Filtro",
                                                modifier = Modifier
                                                    .height(50.dp),
                                                tint = Color.White,
                                            )
                                        }
                                    }
                                }

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
                                                //mapa
//                                        Image(painter = , contentDescription = "mapa da solicitação" )
                                                Text(
                                                    text = "Maria Dolores",
                                                    fontSize = 20.sp,
                                                    fontFamily = customFontFamily,
                                                    fontWeight = FontWeight(600),
                                                    color = Color(0xFF393939),
                                                    textAlign = TextAlign.Center
                                                )
                                                Spacer(modifier = Modifier.width(90.dp))
                                                Text(
                                                    text = "R\$400,00",
                                                    fontSize = 20.sp,
                                                    fontFamily = customFontFamily,
                                                    fontWeight = FontWeight(600),
                                                    color = Color(0xFF3147F5)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(3.dp))
                                            Text(
                                                text = "06600-025, Osasco, SP",
                                                fontFamily = customFontFamily,
                                                fontWeight = FontWeight.Light,
                                                color = Color.Gray
                                            )
                                            Spacer(modifier = Modifier.height(15.dp))
                                            Row(

                                                modifier = Modifier.fillMaxWidth(),

                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Button(
                                                    modifier = Modifier
                                                        .width(145.dp)
                                                        .height(40.dp),
                                                    shape = RoundedCornerShape(size = 12.dp),
                                                    colors = ButtonDefaults.buttonColors(seed),

                                                    onClick = { onCreateService() }
                                                ) {
                                                    Text(
                                                        text = "Iniciar",
                                                        fontFamily = customFontFamily,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }

                                                Spacer(modifier = Modifier.width(42.dp))

                                                Button(
                                                    modifier = Modifier
                                                        .width(145.dp)
                                                        .height(40.dp),
                                                    shape = RoundedCornerShape(size = 12.dp),
                                                    border = BorderStroke(1.dp, Color.Red),
                                                    colors = ButtonDefaults.outlinedButtonColors(
                                                        contentColor = Color.Red
                                                    ),

                                                    onClick = { /*TODO*/ }
                                                ) {
                                                    Text(
                                                        text = "Cancelar",
                                                        fontFamily = customFontFamily,
                                                        fontWeight = FontWeight.Normal
                                                    )

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

            }
        }

    )





}

@Preview(showSystemUi = true)
@Composable
fun DiaristPreview() {

    CleanScreen({})
}



val TAG_HOME = "cliente-home"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.background(Color.Blue),
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Red, shape = RectangleShape),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }

}
