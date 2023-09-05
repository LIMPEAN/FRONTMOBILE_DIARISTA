package br.senai.sp.jandira.limpeanapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_secondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputData(
    label: String,
    myType: KeyboardType? = null,
    state: String,
    whenTyping: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean? = false
) {

        OutlinedTextField(
            value = state,
            onValueChange = {
                whenTyping(it)
            },
            modifier = Modifier
                .width(280.dp)
                .height(60.dp)
            ,

            label = {
                Text(
                    text = label,
                    fontFamily = poopins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = md_theme_dark_secondary
                )
            },
            shape = RoundedCornerShape(60.dp),
            keyboardOptions = KeyboardOptions(keyboardType = myType?: KeyboardType.Text),
            textStyle = TextStyle.Default,
            visualTransformation = visualTransformation,
            isError = isError?: false
        )

    }


@Preview(showBackground = true)
@Composable
fun InputDataPreview() {
    var state by remember {
        mutableStateOf("")
    }
    LimpeanAppTheme {
        Column {
            InputData(label = "Seu melhor Email", myType = KeyboardType.Email,state, whenTyping = { state = it} )
            InputData(label = "Seu melhor Email", myType = KeyboardType.Email,state, whenTyping = { state = it})
        }

    }
}