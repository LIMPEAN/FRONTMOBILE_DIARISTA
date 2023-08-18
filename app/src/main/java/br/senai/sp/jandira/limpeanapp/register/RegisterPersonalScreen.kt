package br.senai.sp.jandira.limpeanapp.register

import android.graphics.drawable.VectorDrawable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.components.Button
import br.senai.sp.jandira.limpeanapp.components.InputData
import br.senai.sp.jandira.limpeanapp.components.SectionTitle
import br.senai.sp.jandira.limpeanapp.domain.CheckPasswordIsSame
import com.example.compose.LimpeanAppTheme
import kotlin.math.log

@Composable
fun RegisterPersonalScreen() {

    var emailState by remember { mutableStateOf("") }
    var firstPasswordState by remember { mutableStateOf("") }
    var confirmationPasswordState by remember { mutableStateOf("") }
    var passwordIsValue by remember {mutableStateOf(false) }
    var phoneState by remember { mutableStateOf("") }


    Column (){
        Spacer(modifier = Modifier.height(60.dp))
        SectionTitle(title = stringResource(id = R.string.register), description = stringResource(id = R.string.register_contact))

        Spacer(modifier = Modifier.height(60.dp))

       Column (
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxWidth(),
       ){
           InputData(
               label = stringResource(R.string.input_email),
               myType = KeyboardType.Email,
               state = emailState,
               whenTyping = { emailState = it}
           )
           Spacer(modifier = Modifier.height(26.dp))
           InputData(
               label = stringResource(R.string.input_password),
               myType = KeyboardType.Password,
               state = firstPasswordState,
               whenTyping = { firstPasswordState = it},
               visualTransformation = PasswordVisualTransformation()
           )
           Spacer(modifier = Modifier.height(26.dp))
           InputData(
               label = stringResource(R.string.input_confirm_password),
               myType = KeyboardType.Password,
               state = confirmationPasswordState,
               visualTransformation = PasswordVisualTransformation(),
               whenTyping = {
                   confirmationPasswordState = it
                   passwordIsValue = CheckPasswordIsSame(confirmationPasswordState, firstPasswordState)

               }
           )
           if(passwordIsValue){
               Text(text = "True", modifier = Modifier.height(26.dp))
           } else { Text(text = "", modifier = Modifier.height(26.dp))}
           InputData(
               label = stringResource(R.string.input_phone),
               myType = KeyboardType.Phone,
               state = phoneState,
               whenTyping = {
                   phoneState = it
               }
           )
           Spacer(modifier = Modifier.height(68.dp))
           Button(name = "Próximo", action = { /*TODO*/ })
      
       }

//        Button(){}
//        CircleDecoration()
        
    }
}


@Preview(showSystemUi = true)
@Composable
fun RegisterPersonalScreenPreview() {
    LimpeanAppTheme {
        RegisterPersonalScreen()
    }
}