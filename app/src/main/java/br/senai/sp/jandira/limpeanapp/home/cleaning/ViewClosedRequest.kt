package br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.home.components.HomeNavBar
import com.example.compose.seed

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun ViewClosedRequest() {

    //lista de comodos pra vir da api
    val checkboxItems = listOf(
        "Quarto",
        "Sala",
        "Cozinha",
        "Banheiro",
        "Escritório",
        "Lavanderia",
        "Garagem",
        "Quintal",
        "Área de lazer"
    )
    val checkedStates = remember { checkboxItems.map { mutableStateOf(false) } }



    data class Address(val id: Int, val street: String, val city: String)

    //fonte
    val customFontFamily = FontFamily(
        Font(R.font.poppins_regular)
    )

    Scaffold(modifier = Modifier.fillMaxSize(),

        topBar = {
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
                                color = Color.White, fontSize = 16.sp, fontFamily = customFontFamily
                            )
                        ) {
                            append("Visualizando a \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 26.sp,
                                fontFamily = customFontFamily
                            )
                        ) {
                            append("Solicitação fechada")
                        }
                    }
                })

            }
        }, bottomBar = {
            HomeNavBar(navController = rememberNavController())

        },

        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = seed)
                    .padding(bottom = 90.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.request_data_service),
                            modifier = Modifier.width(450.dp),
                            fontWeight = FontWeight.Normal,
                            fontSize = 26.sp,
                            fontFamily = customFontFamily
                        )
                        Spacer(modifier = Modifier.height(15.dp))
//
//                        val selectedDate by remember { mutableStateOf(DatePickerState()) }
//
//                        // Converter a data do Calendar para DatePickerState
//                        val calendar = Calendar.getInstance()
//                        selectedDate.value = DatePickerState(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
//
//                        DatePicker(
//                            state = selectedDate.value,
//                            onValueChange = { selectedDate.value = it }
//                        )
//                    }


//                    })



                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                        ) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {

                                }
                                Row() {
                                    Text(
                                        text = stringResource(id = R.string.which_rooms_should_be_cleaned),
                                        fontFamily = customFontFamily,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                Spacer(modifier = Modifier.height(5.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(text = "Nome", color = Color.Gray, fontSize = 12.sp)
                                    Text(text = "Quantidade", color = Color.Gray, fontSize = 12.sp)
                                }

                                Box(
                                    modifier = Modifier
                                        .width(345.dp)
                                        .padding(8.dp)
                                        .height(1.dp)
                                        .background(color = Color.Gray)
                                        .align(Alignment.CenterHorizontally),
                                )


                                Column {

                                    LazyColumn(
                                        modifier = Modifier.height(150.dp),
                                        contentPadding = PaddingValues(12.dp)
                                    ) {

                                        items(checkboxItems.size) { index ->
//                            val quantity = checkedStates[index].value

                                            val quantity = index + 1

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                CheckboxItem(
                                                    text = checkboxItems[index],
                                                    checkedState = checkedStates[index].value,
                                                    onStateChange = {
                                                        checkedStates[index].value = it
                                                    })

                                                QuantityCard(quantity = quantity)

                                                Card(
                                                    modifier = Modifier
                                                        .height(30.dp)
                                                        .width(50.dp),
                                                    shape = RoundedCornerShape(16.dp),
                                                    colors = CardDefaults.cardColors(
                                                        Color(
                                                            49,
                                                            71,
                                                            245
                                                        )
                                                    )
                                                ) {

                                                }
                                            }
                                            Spacer(modifier = Modifier.height(5.dp))

                                        }

                                    }
                                }

                                Spacer(modifier = Modifier.height(15.dp))


                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)

                                ) {
                                    Text(
                                        text = stringResource(id = R.string.type_cleaning),
                                        fontFamily = customFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {

                                        RadioButton(
                                            selected = false,
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier

                                        )
                                        Text(
                                            text = "Padrão", fontFamily = customFontFamily
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.question_children_or_animals),
                                        fontFamily = customFontFamily,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Start

                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        RadioButton(
                                            selected = false,
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier

                                        )
                                        Text(text = "Nenhum", fontFamily = customFontFamily)
                                    }
                                    Text(
                                        text = stringResource(id = R.string.date),
                                        fontFamily = customFontFamily,
                                        fontSize = 16.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Button(modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                        .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                242, 242, 242
                                            )
                                        ),
                                        shape = RoundedCornerShape(size = 12.dp),
                                        onClick = { /*TODO*/ }) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.CalendarMonth,
                                                contentDescription = "Calendário",
                                                tint = Color(83, 87, 90)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                "11:00", color = Color(83, 87, 90)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = stringResource(id = R.string.start_time),
                                    fontFamily = customFontFamily,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                                    .height(50.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            242, 242, 242
                                        )
                                    ),
                                    shape = RoundedCornerShape(size = 12.dp),
                                    onClick = { /*TODO*/ }) {
                                    Row(modifier = Modifier.fillMaxWidth()) {

                                        Icon(
                                            imageVector = Icons.Outlined.Timer,
                                            contentDescription = "Relógio",
                                            tint = Color(83, 87, 90)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            "11:00", color = Color(83, 87, 90)
                                        )
                                    }

                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = stringResource(id = R.string.define_time),
                                    fontFamily = customFontFamily,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    val text = rememberSaveable { mutableStateOf("") }

                                    val textFieldState = rememberUpdatedState("")

                                    OutlinedTextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        value = text.value,
                                        onValueChange = { text.value = it },
                                        shape = RoundedCornerShape(size = 12.dp),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Done
                                        ),
//                                        KeyboardOptions(keyboardType = KeyboardType.Number),
                                        leadingIcon = {
                                          Icon(modifier = Modifier.padding(start = 25.dp),
                                              imageVector = Icons.Outlined.Timer,
                                              contentDescription = "Relógio",
                                              tint = Color(83, 87, 90)
                                              )
                                        }
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = stringResource(id = R.string.define_value),
                                    fontFamily = customFontFamily,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(modifier = Modifier.fillMaxWidth()) {

                                    val text = rememberSaveable { mutableStateOf("") }

                                    OutlinedTextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        value = text.value, onValueChange = { text.value = it },
                                        shape = RoundedCornerShape(size = 12.dp),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        leadingIcon = {
                                            Icon(modifier = Modifier.padding(start = 25.dp),
                                                imageVector = Icons.Outlined.AttachMoney,
                                                contentDescription = "Dinheiro",
                                                tint = Color(83, 87, 90)
                                            )
                                        }
                                    )
                                }

                                Spacer(modifier = Modifier.height(20.dp))


                                Row(

                                    modifier = Modifier.fillMaxWidth(),

                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        modifier = Modifier
                                            .width(145.dp)
                                            .height(40.dp),
                                        shape = RoundedCornerShape(size = 18.dp),
                                        border = BorderStroke(1.dp, Color.Red),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Text(
                                            text = "Recusar",
                                            fontFamily = customFontFamily,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(42.dp))

                                    Button(
                                        modifier = Modifier
                                            .width(145.dp)
                                            .height(40.dp),
                                        shape = RoundedCornerShape(size = 18.dp),
                                        colors = ButtonDefaults.buttonColors(seed),
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Text(
                                            text = "Enviar",
                                            fontFamily = customFontFamily,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
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
fun ViewClosedRequestPreview() {
    ViewClosedRequest()

}