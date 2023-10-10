package br.senai.sp.jandira.limpeanapp.authentication.componentes


import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    state: TextFieldState
) {

    Column {
        OutlinedTextField(
            value = state.value,
            onValueChange = { state.change(it) },
            isError = state.hasError,
            singleLine = true,
            modifier = Modifier.width(368.dp),
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(
                    text = state.name.uppercase(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF53575A),
                    )
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (state.hasError) {
            Text(
                text = state.errorMessage,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = md_theme_light_error,
                )
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun CaixaDeTextoPreview() {
    LimpeanAppTheme {

        val context = LocalContext.current
        val state: TextFieldState = TextFieldState("nome", "")

        Column (horizontalAlignment = Alignment.CenterHorizontally){
            CaixaDeTexto(state)
            MyButton(name = "Submit", onClick = {
                if (state.validate()) {
                    Toast.makeText(
                        context,
                        "Campo válido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    }


    var nome by remember {
        mutableStateOf("teste")
    }
    var senhaState by remember {
        mutableStateOf("")
    }
    var seVisivel by remember {
        mutableStateOf(false)
    }


}


