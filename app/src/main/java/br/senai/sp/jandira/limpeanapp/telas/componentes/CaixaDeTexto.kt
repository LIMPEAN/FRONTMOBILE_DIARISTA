package br.senai.sp.jandira.limpeanapp.telas.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    etiqueta : String,
    estado : String,
    aoDigitar : (String) -> Unit,
    transformacaoVisual : VisualTransformation = VisualTransformation.None,
    tipoSenha : Boolean = false,
    visivel : Boolean = false
) {



       if(tipoSenha){
           OutlinedTextField(
               value = estado,
               onValueChange = {
                   aoDigitar(it)
               },
               singleLine = true,
               shape = RoundedCornerShape(8.dp),
               label = {
                   Text(
                       text = etiqueta,
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
               shape = RoundedCornerShape(8.dp),
               label = {
                   Text(
                       text = etiqueta,
                   )
               },
               visualTransformation = transformacaoVisual,
               keyboardOptions = KeyboardOptions.Default
           )
       }


}

@Preview(showBackground = true)
@Composable
fun CaixaDeTextoPreview() {
    var nome by remember {
        mutableStateOf("")
    }
    var senhaState by remember {
        mutableStateOf("")
    }
    var seVisivel by remember {
        mutableStateOf(false)
    }


}

