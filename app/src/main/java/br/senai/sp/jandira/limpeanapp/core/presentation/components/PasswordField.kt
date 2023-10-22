package br.senai.sp.jandira.limpeanapp.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme


@Composable
fun PasswordField (
    labelText : String,
    state : String,
    onValueChange : (String) -> Unit,
    modifier : Modifier = Modifier.fillMaxWidth()
){
    var passVisibilityState by remember {
        mutableStateOf(true)
    }

    OutlinedTextField(
        value = state,

        modifier = modifier,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = labelText,

//                style = TextStyle(
////                    fontSize = 12.sp,
////                    lineHeight = 16.sp,
////                    fontFamily = FontFamily(Font(R.font.inter)),
////                    fontWeight = FontWeight(400),
////                    color = Color(0xFF53575A),
            )

        },
        singleLine = true,
//        modifier = Modifier
//            .width(368.dp),

        shape = RoundedCornerShape(8.dp),

        visualTransformation = if (passVisibilityState)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = {
                passVisibilityState = !passVisibilityState
            }) {
                Icon(
                    imageVector = if (passVisibilityState)
                        Icons.Default.Visibility
                    else
                        Icons.Default.VisibilityOff,
                    contentDescription = "show"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CaixaDeSenhaPreview() {
    var passwordState by remember { mutableStateOf("") }
    LimpeanAppTheme {
        PasswordField(
            labelText = "Senha",
            state = passwordState,
            onValueChange = {passwordState = it}
        )

    }

}