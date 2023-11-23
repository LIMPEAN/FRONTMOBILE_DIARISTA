package br.senai.sp.jandira.limpeanapp.ui.components.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.StarView
//import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.StarView
import com.example.compose.seed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssentmentDialog(
    name: String,
    message: String,
    onDismiss: () -> Unit,
    onPositivePositionButtonClicked: () -> Unit,
    onPositiveNegativeButtonClicked: () -> Unit,
//    properties: DialogProperties
) {
    val customFontFamily = FontFamily(
        Font(R.font.poppins_regular)
    )

    val text = rememberSaveable { mutableStateOf("") }
    var rating by remember { mutableDoubleStateOf(0.0) }




    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(

        )
    ) {
        Card(
            modifier = androidx.compose.ui.Modifier
                .width(500.dp)
                .height(340.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                Color.White
            )
//            colors = CardColors(
//                containerColor = Color.Black,
//                contentColor = Color.Blue,
//                disabledContainerColor = Color.Yellow,
//                disabledCon
//            )

        )
        {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {

                Row(

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = "",
                        modifier = androidx.compose.ui.Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = androidx.compose.ui.Modifier.width(18.dp))
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle()) {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Light,
                                            fontFamily = customFontFamily
                                        )
                                    ) {
                                        append("Como foi o serviço de ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontFamily = customFontFamily
                                        )
                                    ) {
                                        append("$name")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Light,
                                            fontFamily = customFontFamily
                                        )
                                    ) {
                                        append("?")
                                    }
                                }
                            }
                        )

                        Text(
                            text = "nos ajude a ranquear a plataforma e avalie o diarista",
                            color = Color(red = 143, green = 143, blue = 143),
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                        )

                        Spacer(modifier = androidx.compose.ui.Modifier.height(7.dp))

                        StarView(
                            rating = rating,
                            onRatingChanged = { newRating ->
                                rating = newRating
                            }
                        )
                    }
                }

                Spacer(modifier = androidx.compose.ui.Modifier.height(15.dp))



                OutlinedTextField(
                    value = text.value, onValueChange = { text.value = it },
                    modifier = androidx.compose.ui.Modifier
                        .height(100.dp),
                    placeholder = {
                        Text(
                            text = "Escreva um breve comentário sobre seu atendimento",
                            color = Color(red = 163, green = 163, blue = 163),
                            fontSize = 10.sp,
                            lineHeight = 13.sp
                        )

                    },
                    maxLines = 5,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color(red = 244, green = 244, blue = 244),
                        focusedIndicatorColor = Color(red = 176, 176, 176),
                        disabledContainerColor = Color(red = 244, green = 244, blue = 244),
                        unfocusedContainerColor = Color(red = 244, green = 244, blue = 244),
                        focusedContainerColor = Color(red = 244, green = 244, blue = 244)
                    )

                )

                Spacer(modifier = androidx.compose.ui.Modifier.height(22.dp))

                Button(
                    onClick = {
                    },
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = seed),
                    shape = RoundedCornerShape(size = 12.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.authenticate),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }


            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun CustomDialogPreview() {
    AssentmentDialog(
        name = "Camila",
        message = "fashlja",
        onDismiss = { /*TODO*/ },
        onPositivePositionButtonClicked = { /*TODO*/ },
        onPositiveNegativeButtonClicked = { /*TODO*/ },
//        properties = ""
    )

}