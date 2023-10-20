package br.senai.sp.jandira.limpeanapp.core.presentation.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R


@Composable
fun NormalTextField(
    label: String,
    keyboardType: KeyboardType,
    state: String,
    onTyping : (String) -> Unit,
    modifier : Modifier = Modifier.fillMaxWidth()
){

    OutlinedTextField(
        modifier = modifier,
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
        shape = RoundedCornerShape(size = 8.dp)
    )
}