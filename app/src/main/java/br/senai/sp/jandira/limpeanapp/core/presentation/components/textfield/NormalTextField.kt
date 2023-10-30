package br.senai.sp.jandira.limpeanapp.core.presentation.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme


@Composable
fun NormalTextField(
    labelText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
){

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = labelText,
                style = MaterialTheme.typography.labelMedium
            )
        }
        ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(size = 8.dp)

    )
}

@Preview(showSystemUi = true)
@Composable
fun NormalTextFieldPreview() {
    var state by remember {
        mutableStateOf("Testando")
    }
    LimpeanAppTheme {
        NormalTextField(
            labelText = "Teste",
            value = state,
            onValueChange = {state = it}
        )
    }
}