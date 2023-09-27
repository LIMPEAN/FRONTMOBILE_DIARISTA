package br.senai.sp.jandira.limpeanapp.telas.componentes


import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_error
import com.example.compose.md_theme_light_error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    etiqueta : String,
    estado : String,
    aoDigitar : (String) -> Unit,
    transformacaoVisual : VisualTransformation = VisualTransformation.None,
    tipoSenha : Boolean = false,
    visivel : Boolean = false,
    temErro : Boolean = false,
    mensagemDeErro : String = ""
) {

    Column {
        OutlinedTextField(
            value = estado,
            onValueChange = { aoDigitar(it) },
            isError = temErro,
            singleLine = true,
            modifier = Modifier.width(368.dp),
            shape = RoundedCornerShape(8.dp),
            label = {
                Text(
                    text = etiqueta,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF53575A),
                    )
                )
            },
            visualTransformation = if (visivel) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if(temErro){
            Text(
                text = mensagemDeErro,
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
      var state by remember { mutableStateOf("")}
      var stateError by remember { mutableStateOf(true) }
      var stateMessage by remember { mutableStateOf("teste") }

      TelaDeCadastro(titulo = "Perfil") {
          Column {

              CaixaDeTexto(
                  etiqueta = "Email",
                  estado = state,
                  aoDigitar = {state = it},
                  temErro = stateError,
                  mensagemDeErro = stateMessage
              )
              Button(name = "Submit", action = {
                  when {
                      state.isEmpty() -> {
                          stateError = true
                          stateMessage = "Não pode ser vazio!"
                      }
                  }
              })
      }

      }


  }
}

