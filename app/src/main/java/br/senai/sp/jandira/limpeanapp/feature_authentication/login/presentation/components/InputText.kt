package br.senai.sp.jandira.limpeanapp.feature_authentication.login.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inputText(
    label: String,
    keyboardType: KeyboardType,
    state: String,
    onTyping : (String) -> Unit,

    ) {
    OutlinedTextField(
        value = state,
        onValueChange = {
            onTyping(it)
        },
        label = {
            Text(
                text = label,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = Color(0xFF53575A),
                textAlign = TextAlign.Center
            )
        }
        ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(size = 15.dp)
    )
}


@Preview()
@Composable
fun InputTextPreview(){
    var state by remember {
        mutableStateOf("")
    }
    LimpeanAppTheme {
        Column {
            inputText(label = "NOME", keyboardType = KeyboardType.Text, state = state, onTyping ={
                state = it
            } )
            inputText(label = "NOME", keyboardType = KeyboardType.Text, state = state, onTyping ={
                state = it
            } )
            inputText(label = "NOME", keyboardType = KeyboardType.Text, state = state, onTyping ={
                state = it
            } )
        }


    }
}