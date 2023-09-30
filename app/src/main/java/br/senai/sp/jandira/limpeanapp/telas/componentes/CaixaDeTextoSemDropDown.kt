package br.senai.sp.jandira.limpeanapp.telas.componentes

<<<<<<< HEAD
=======
import androidx.compose.foundation.layout.Column
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
<<<<<<< HEAD
=======
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
<<<<<<< HEAD
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
=======
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
import com.example.compose.LimpeanAppTheme
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTextoSemDropDown(
    etiqueta: String,
    estado: String,
    aoDigitar: (String) -> Unit,
    transformacaoVisual: VisualTransformation = VisualTransformation.None,
    tipoSenha: Boolean = false,
    visivel: Boolean = false,
) {

    if(tipoSenha){
        OutlinedTextField(
            value = estado,
            onValueChange = {
                aoDigitar(it)
            },
            singleLine = true,
            modifier = Modifier
                .width(368.dp),
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
    } else {
        OutlinedTextField(
            value = estado,
            onValueChange = {
                aoDigitar(it)
            },
            singleLine = true,
            modifier = Modifier
                .width(368.dp)

            ,
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
            visualTransformation = transformacaoVisual,
            keyboardOptions = KeyboardOptions.Default
        )
    }


}