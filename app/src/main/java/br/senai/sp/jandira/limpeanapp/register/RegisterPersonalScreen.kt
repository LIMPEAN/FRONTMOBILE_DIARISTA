package br.senai.sp.jandira.limpeanapp.register

import android.renderscript.ScriptGroup.Input
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.components.InputData
import br.senai.sp.jandira.limpeanapp.components.SectionTitle
import com.example.compose.LimpeanAppTheme

@Composable
fun RegisterPersonalScreen() {

    val emailState by remember { mutableStateOf("") }
    val firstPasswordState by remember { mutableStateOf("") }
    val confirmationPasswordState by remember { mutableStateOf("") }
    val phoneState by remember { mutableStateOf("") }

    Column (){
        Spacer(modifier = Modifier.height(60.dp))
        SectionTitle(title = stringResource(id = R.string.register), description = stringResource(id = R.string.register_contact))

        Spacer(modifier = Modifier.height(60.dp))



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