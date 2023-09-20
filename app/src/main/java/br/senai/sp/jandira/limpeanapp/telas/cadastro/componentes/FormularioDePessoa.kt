package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.use_cases.ValidateName
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDePessoa(
    salvarDados : (Pessoa) -> Unit
) {
    var person = Pessoa()
    var name by remember { mutableStateOf(person.nome) }
    var cpf by remember { mutableStateOf(person.cpf) }
    var rg by remember { mutableStateOf(person.rg) }
    var dateOfBirth by remember { mutableStateOf(person.dataDeNascimento) }
    var gender by remember { mutableStateOf(person.genero) }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = , onValueChange = {  },
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            shape = RoundedCornerShape(size = 40.dp)
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
                ),
            shape = RoundedCornerShape(size = 40.dp)
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
                ),
            shape = RoundedCornerShape(size = 40.dp)
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
                ),
            shape = RoundedCornerShape(size = 40.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "Data Nascimento", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            shape = RoundedCornerShape(size = 40.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "Genero", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            shape = RoundedCornerShape(size = 40.dp)
        )

        Spacer(modifier = Modifier.height(150.dp))

        BotaoDeCadastro(nomeDaAcao = "Continuar") {
            val pessoa = Pessoa(
                /* Adicione os dados aqui */
                nome = "Felipe"
            )
            salvarDados(pessoa)
        }

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
fun FormularioPreview() {
    LimpeanAppTheme {
        FormularioDePessoa({})
    }

}



