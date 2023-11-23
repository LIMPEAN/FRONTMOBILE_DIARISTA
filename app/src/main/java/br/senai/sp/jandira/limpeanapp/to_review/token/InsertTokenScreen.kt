package br.senai.sp.jandira.limpeanapp.feature_diarist.token

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme
import com.example.compose.seed



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InsertTokenScreen(
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
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Quase lá \n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("Antes de iniciar")
                            }
                        }
                    }
                )

            }
        },
        bottomBar = {
//           Text(text = "footer")
        },

        content = {

            GenerateTokenContent()


        }

    )


}

@Composable
fun GenerateTokenContent() {
    val customFontFamily = Poppins
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.request_token),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontFamily = customFontFamily,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(modifier = Modifier.height(230.dp),
                painter = painterResource(id = R.drawable.authenticate),
                contentDescription = "imagem "
            )

            Text(
                text = stringResource(id = R.string.description_request_token),
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )

            Row(modifier = Modifier.fillMaxWidth()) {

                val text = rememberSaveable { mutableStateOf("") }

                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    value = text.value, onValueChange = {text.value = it},
                    label = { Text(text = stringResource(id = R.string.authenticate_token))})
            }

            Spacer(modifier = Modifier.height(12.dp))


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

            Spacer(modifier = Modifier.height(10.dp))

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

@Preview
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TokenPreview() {
    LimpeanAppTheme {
        GenerateTokenContent()
    }
}

