package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.regras.Pessoa

import br.senai.sp.jandira.limpeanapp.regras.use_cases.ValidateName
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onPrimary
import com.example.compose.md_theme_light_scrim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDeUsuario(

) {

    var person = Pessoa()
    var name by remember { mutableStateOf(person.nome) }
    var cpf by remember { mutableStateOf(person.cpf) }
    var dateOfBirth by remember { mutableStateOf(person.dataDeNascimento) }
    var gender by remember { mutableStateOf(person.genero) }



    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = "NOME", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                )
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "CPF", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                )
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "RG", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                )
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "TELEFONE", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                )
        )

    }



}



// Function to validate a name (you can customize your validation logic)
private fun isValidName(name: String): Boolean {
    return ValidateName()
        .execute(name)
        .successful
}

@Preview(showSystemUi = true)
@Composable
fun FormularioUsuarioPreview() {
    LimpeanAppTheme {
        FormularioDeUsuario()
    }

}