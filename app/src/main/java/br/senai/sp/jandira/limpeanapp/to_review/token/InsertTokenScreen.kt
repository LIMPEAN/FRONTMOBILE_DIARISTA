package br.senai.sp.jandira.limpeanapp.feature_diarist.token

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.Dialog
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme
import com.example.compose.seed
import org.w3c.dom.Text


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

            GenerateTokenContent(
                {}
            )


        }

    )


}

@Composable
fun GenerateTokenContent(
    onBackPress : () -> Unit = {},
    token : String = "token aqui"
) {
    val customFontFamily = Poppins
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(32.dp)
    ) {

        Column(
            modifier = Modifier.padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.request_token),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontFamily = customFontFamily,
                style = MaterialTheme.typography.bodyLarge
            )
            Image(modifier = Modifier.height(230.dp),
                painter = painterResource(id = R.drawable.authenticate),
                contentDescription = "imagem "
            )

            Text(
                text = stringResource(id = R.string.description_request_token),
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = token,
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(id = R.string.authenticate_token),
                        fontFamily = Poppins
                    )
                }
            )


            Button(
                onClick = { onBackPress()},
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, colorResource(id = R.color.outlined_button_red)),
                shape = RoundedCornerShape(8.dp),
            ){
                Text(
                    text = stringResource(R.string.voltar).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.outlined_button_red)
                )
            }
        }
    }
}

@Preview
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TokenPreview() {
    LimpeanAppTheme {
        var isShowDialog by remember {
            mutableStateOf(true)
        }
        var token by remember {
            mutableStateOf("126dionv3")
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { isShowDialog = true}) {
                Text(text = "Ver token")
            }
        }
        if(isShowDialog){
            Dialog(onDismissRequest = { isShowDialog = false}) {
                GenerateTokenContent(
                    onBackPress = {isShowDialog = false},
                    token = token
                )
            }
        }
    }
}

