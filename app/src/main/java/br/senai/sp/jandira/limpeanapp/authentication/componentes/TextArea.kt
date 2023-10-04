package br.senai.sp.jandira.limpeanapp.authentication.componentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextArea() {
        val text = rememberSaveable { mutableStateOf("") }
        TextField(
            value = text.value,
            onValueChange = { text.value = it }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        )

}

@Preview
@Composable
fun TextAreaPreview() {
    LimpeanAppTheme {
        TextArea()
    }
}