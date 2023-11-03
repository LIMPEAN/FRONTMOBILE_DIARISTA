package br.senai.sp.jandira.limpeanapp.feature_diarist.token

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
import com.example.compose.seed

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun ReportServiceScreen() {


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
                                append("Pronto \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Em andamento")
                            }
                        }
                    }
                )

            }
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
                            text = stringResource(id = R.string.report_service_status),
                            modifier = Modifier.width(450.dp),
                            fontWeight = FontWeight.Medium,
                            fontSize = 26.sp,
                            fontFamily = customFontFamily
                        )

                        Image(
                            painter = painterResource(id = R.drawable.time_flies),
                            contentDescription = "",
                            modifier = Modifier
                                .size(256.dp)
                                .align(Alignment.CenterHorizontally
                                )
                        )

                        Text(
                            text = stringResource(id = R.string.tasks),
                            modifier = Modifier.width(450.dp),
                            color = colorResource(id = R.color.grey_font),
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            fontFamily = customFontFamily
                        )

                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(containerColor = seed),
                            shape = RoundedCornerShape(size = 8.dp),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = stringResource(id = R.string.view),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }




                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                                    .background(Color(red = 227, green = 227, blue = 227))
                            )
                            Text(
                                text = stringResource(id = R.string.something_went_wrong),
                                modifier = Modifier.padding(14.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(red = 100, green = 102, blue = 102)
                            )
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                                    .background(Color(red = 227, green = 227, blue = 227))
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .width(158.dp)
                                    .height(40.dp)
                                    .background(Color.White),
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                border = BorderStroke(1.dp, colorResource(id = R.color.outlined_button_red)),
                                shape = RoundedCornerShape(8.dp),
                            ){
                                Text(
                                    text = stringResource(id = R.string.cancel),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.outlined_button_red)
                                )
                            }

                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .width(158.dp)
                                    .height(40.dp)
                                    .background(Color.White),
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                border = BorderStroke(1.dp, colorResource(id = R.color.outlined_button_yellow)),
                                shape = RoundedCornerShape(8.dp),
                            ){
                                Text(
                                    text = stringResource(id = R.string.report),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.outlined_button_yellow)
                                )
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
fun ReportServicePreview() {
    ReportServiceScreen()

}


