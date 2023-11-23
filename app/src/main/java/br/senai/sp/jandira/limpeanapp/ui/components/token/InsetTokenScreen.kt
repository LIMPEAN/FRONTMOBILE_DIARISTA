package br.senai.sp.jandira.limpeanapp.ui.components.token

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseToken
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.PhoneDto
//import br.senai.sp.jandira.limpeanapp.ui.features.profile.ProfileViewModel
import com.example.compose.seed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class InsetTokenViewModel @Inject constructor(
    private val api: DiaristApi
) : ViewModel() {


    var resultado by mutableStateOf<BaseResponseToken?>(null)
        private set


    suspend fun carregarToken() {

            val token = api.getToken(1)
            resultado = token

            Log.i("TESTE2", resultado.toString())

    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InsertTokenScreen(
    onSelectTypeRequest: () -> Unit,
    viewModel : InsetTokenViewModel = hiltViewModel<InsetTokenViewModel>()
) {

    runBlocking {
        viewModel.carregarToken()
    }



    val resultado = viewModel.resultado
    val api : DiaristApi

    Scaffold(modifier = Modifier.fillMaxSize(),

        topBar =
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .padding(start = 24.dp, bottom = 85.dp)
                    .offset(x = 6.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(buildAnnotatedString {
                    withStyle(style = ParagraphStyle()) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 16.sp,
//                                fontFamily = customFontFamily
                            )
                        ) {
                            append("Quase lá \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 28.sp,
//                                fontFamily = customFontFamily
                            )
                        ) {
                            append("Antes de iniciar")
                        }
                    }
                })

            }
        }, bottomBar =
        {
//           Text(text = "footer")
        },

        content =
        {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = seed),
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.request_token),
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 26.sp,
//                            fontFamily = customFontFamily
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            modifier = Modifier.height(230.dp),
                            painter = painterResource(id = R.drawable.authenticate),
                            contentDescription = "imagem "
                        )

                        Text(
                            text = stringResource(id = R.string.description_request_token),
//                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Light,
                            color = Color(red = 100, green = 102, blue = 102)
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {

                            val text = rememberSaveable { mutableStateOf("") }

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = text.value,
                                onValueChange = { text.value = it },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.authenticate_token)
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color(
                                        red = 83,
                                        green = 87,
                                        blue = 70
                                    ),
                                    focusedIndicatorColor = seed,
                                    disabledContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    focusedLabelColor = seed
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))


                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(containerColor = seed),
                            shape = RoundedCornerShape(size = 8.dp),
                            onClick = {

                            }) {
                            Text(
                                text = "AUTENTICAR",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))



                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(Color.White),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            border = BorderStroke(
                                1.dp, colorResource(id = R.color.outlined_button_red)
                            ),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.outlined_button_red)
                            )
                        }


                    }

                }
            }


        }

    )


}


@Preview(showSystemUi = true)
@Composable
fun InsertTokenPreview() {
    InsertTokenScreen({})

}
