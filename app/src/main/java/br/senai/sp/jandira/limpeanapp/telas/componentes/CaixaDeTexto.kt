package br.senai.sp.jandira.limpeanapp.telas.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    etiqueta : String,
    estado : String,
    aoDigitar : (String) -> Unit,

) {

    OutlinedTextField(
        value = estado,
        onValueChange = {
            aoDigitar(it)
        },
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(
                text = etiqueta,
            )
        },
        keyboardOptions = KeyboardOptions.Default
    )
}

@Preview(showBackground = true)
@Composable
fun CaixaDeTextoPreview() {
    var nome by remember {
        mutableStateOf("")
    }
    LimpeanAppTheme {
        CaixaDeTexto(estado = nome , aoDigitar =  { nome = it}, etiqueta = "nome")
    }

}