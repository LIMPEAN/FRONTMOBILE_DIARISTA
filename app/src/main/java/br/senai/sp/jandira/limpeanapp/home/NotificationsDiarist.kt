package br.senai.sp.jandira.limpeanapp.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
//import br.senai.sp.jandira.limpeanapp.feature_client.components.NavBar
import com.example.compose.seed

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun NotificationsDiarist() {
    val listItems = listOf(
        "Carlos Pereira",
        "José Luis",
        "Camila Alves",
        "Bianca Fruiz",
        "Estevao Luis",
        "Lis Oliveira"
    )
    val listtStates = remember { listItems.map { mutableStateOf(false) } }

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
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Visualize suas \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Notificações")
                            }
                        }
                    }
                )

            }

        }, bottomBar = {
//            NavBar()
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

                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.view_notifications),
                            modifier = Modifier.width(450.dp),
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


                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(bottom = 90.dp),
                            contentPadding = PaddingValues(12.dp)
                        ) {

                            items(listItems.size) { index ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Card(
                                        modifier = Modifier
                                            .height(78.dp)
                                            .fillMaxWidth()
                                            .clickable {
                                            },
                                        colors = CardDefaults.cardColors(Color.White),
//                                        border = BorderStroke(bottom ,width = 1.dp, color = Color(R.color.stroke_notifications))

                                    ) {

                                        Row (
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceAround
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.image_man_profile),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .clip(shape = CircleShape)
                                            )

                                            Row(modifier = Modifier.width(200.dp)) {
                                                Text(

                                                    buildAnnotatedString {
                                                        withStyle(style = ParagraphStyle()) {
                                                            withStyle(
                                                                style = SpanStyle(
                                                                    color = Color.Blue,
                                                                    fontSize = 10.sp,
                                                                    fontFamily = customFontFamily
                                                                )
                                                            ) {
                                                                append("${listItems[index]} ")
                                                            }

                                                            withStyle(
                                                                style = SpanStyle(
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = Color.Black,
                                                                    fontSize = 10.sp,
                                                                    fontFamily = customFontFamily
                                                                )
                                                            ) {
                                                                append("definiu o valor da solicitação de número")
                                                            }

                                                            withStyle(
                                                                style = SpanStyle(
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = Color.Black,
                                                                    fontSize = 10.sp,
                                                                    fontFamily = customFontFamily
                                                                )
                                                            ) {
                                                                append("#${listItems.size}")
                                                            }

                                                            withStyle(
                                                                style = SpanStyle(
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = Color.Black,
                                                                    fontSize = 10.sp,
                                                                    fontFamily = customFontFamily
                                                                )
                                                            ) {
                                                                append("para")
                                                            }

                                                            withStyle(
                                                                style = SpanStyle(
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = Color.Black,
                                                                    fontSize = 10.sp,
                                                                    fontFamily = customFontFamily
                                                                )
                                                            ) {
                                                                append("R$300,00")
                                                            }
                                                        }
                                                    }
                                                )
                                            }


                                            Image(painter = painterResource(id = R.drawable.more_vert),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(16.dp))
                                        }

                                    }

                                }
                                Row(
                                    Modifier
                                        .height(0.5.dp)
                                        .fillMaxWidth()
                                        .background(Color.Gray)) {
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
fun NotificationsDiaristPreview() {
    NotificationsDiarist()

}


