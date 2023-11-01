package br.senai.sp.jandira.limpeanapp.feature_diarist.cleaning


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
import br.senai.sp.jandira.limpeanapp.home.presentation.home.components.HomeContent
import br.senai.sp.jandira.limpeanapp.home.presentation.home.components.HomeSection
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.seed


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CleaningDetailScreen() {

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
                            append("Solicitação aberta")
                        }
                    }
                })

            }
        }, bottomBar = {
//           Text(text = "footer")
        },

        content = {
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

                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.request_data_service),
                            modifier = Modifier.width(450.dp),
                            fontWeight = FontWeight.Normal,
                            fontSize = 26.sp,
                            fontFamily = customFontFamily
                        )
                        Spacer(modifier = Modifier.height(15.dp))

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

                                                CheckboxItem(text = checkboxItems[index],
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
                                        .height(40.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                242, 242, 242
                                            )
                                        ),
                                        shape = RoundedCornerShape(size = 12.dp),
                                        onClick = { /*TODO*/ }) {
                                        Row (
                                            modifier = Modifier.fillMaxWidth(),
                                        ){
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
                                    .height(40.dp),
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
                                    text = stringResource(id = R.string.end_time),
                                    fontFamily = customFontFamily,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                                    .height(40.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            242, 242, 242
                                        )
                                    ),
                                    shape = RoundedCornerShape(size = 12.dp),
                                    onClick = { /*TODO*/ }) {
                                    Row(modifier = Modifier.fillMaxWidth()
                                    ){
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
                                    text = stringResource(id = R.string.set_value),
                                    fontFamily = customFontFamily,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                                    .height(40.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            242, 242, 242
                                        )
                                    ),
                                    shape = RoundedCornerShape(size = 12.dp),
                                    onClick = { /*TODO*/ }) {
                                    Row(modifier = Modifier.fillMaxWidth()
                                    ){
                                        Icon(
                                            imageVector = Icons.Outlined.AttachMoney,
                                            contentDescription = "Valor",
                                            tint = Color(83, 87, 90)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            "300,00", color = Color(83, 87, 90)
                                        )
                                    }

                                }

                                Spacer(modifier = Modifier.height(15.dp))


                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .align(Alignment.CenterHorizontally),
                                    colors = ButtonDefaults.buttonColors(containerColor = seed),
                                    onClick = { /*TODO*/ }) {
                                    Text( text = stringResource(id = R.string.button_accept))
                                }
                            }

                            }



                    }

                }
            }
}
)

}

@Composable
fun QuantityCard(quantity: Int) {
    Card(
        modifier = Modifier
            .height(30.dp)
            .width(70.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color(49, 71, 245))
    ) {

        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = quantity.toString(), color = Color.White, fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun CheckboxItem(

    text: String,
    checkedState: Boolean,
    onStateChange: (Boolean) -> Unit,
//    onRemoveClick: () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(seed),
            checked = true, onCheckedChange = null
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(100.dp)
        )
        Spacer(modifier = Modifier.width(55.dp))

        //passar quantidade vindo da api
        val q = 2
        QuantityCard(q)
    }

}


//@Preview(showSystemUi = true)
//@Composable
//fun ViewOpenRequestPreview() {
//    CleaningDetailScreen()
//
//}
@Preview(showBackground = true)
@Composable
fun CleaningDetail() {
    LimpeanAppTheme {
        val modifier = Modifier.fillMaxWidth()
        val horizontalArrangement = Arrangement.SpaceBetween
        Column {
            HomeSection(
                title = "Dados do Serviço"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(32.dp)

                ) {
                    SubSection(text = "Valor definido") {
                        Text(text = "200.00")
                    }
                    SubSection(text = "Data da Faxina") {
                        Text(text = "20")
                    }
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(33.dp)
                ) {
                    SubSection(text = "Hora de Início"){
                        Text(text = "14:00h")
                    }
                    SubSection(text = "Hora de Término"){
                        Text(text = "18:00h")
                    }
                }


            }
            HomeSection(title = "Endereço") {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(33.dp)
                ) {
                    SubSection(text = "Logradouro"){
                        Text(text = "Rua das Flores")
                    }
                    SubSection(text = "Bairro"){
                        Text(text = "Jardim Cotia")
                    }
                    SubSection(text = "Cidade"){
                        Text(text = "Cotia")
                    }
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(33.dp)
                ) {
                    SubSection(text = "Complemento"){
                        Text(text = "274")
                    }
                    SubSection(text = "Estado"){
                        Text(text = "São Paulo")
                    }
                }
            }

            HomeSection(title = "Sobre o Cliente") {
                
            }
            HomeSection(title = "Suporte a faxina") {

            }
            HomeSection(
                title = "Gostou do Serviço?"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Sim, eu quero!")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Não gostei :(")
                    }
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Quero enviar uma proposta.")
                }

            }

        }

    }
}

@Composable
fun SubSection(
    text : String,
    content: @Composable ()-> Unit
) {
    Column() {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
        Spacer(modifier = Modifier.height(12.dp))
    }

}