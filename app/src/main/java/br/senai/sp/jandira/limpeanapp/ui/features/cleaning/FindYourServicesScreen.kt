package br.senai.sp.jandira.limpeanapp.ui.features.cleaning

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FindYourServicesScreen() {
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
                                append("Fique de olho! \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Seviços agendados")
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
                            text = stringResource(id = R.string.find_your_services),
                            modifier = Modifier.width(450.dp),
                            fontWeight = FontWeight.Medium,
                            fontSize = 26.sp,
                            fontFamily = customFontFamily
                        )
                    }
                }
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun FindYourServicesPreview() {
    FindYourServicesScreen()

}
