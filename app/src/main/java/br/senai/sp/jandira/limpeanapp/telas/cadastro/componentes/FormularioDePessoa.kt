package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.regras.Person
import br.senai.sp.jandira.limpeanapp.regras.use_cases.ValidateName
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDePessoa(

) {
    var person = Person()
    var name by remember { mutableStateOf(person.name) }
    var cpf by remember { mutableStateOf(person.cpf) }
    var rg by remember { mutableStateOf(person.rg) }
    var dateOfBirth by remember { mutableStateOf(person.dateOfBirth) }
    var gender by remember { mutableStateOf(person.gender) }


    Text(text = "Campos de Texto de Pessoa")



}
// Function to validate a name (you can customize your validation logic)
private fun isValidName(name: String): Boolean {
    return ValidateName()
        .execute(name)
        .successful
}


@Preview(showSystemUi = true)
@Composable
fun NameTextFieldPreview() {
    var nameValue by remember { mutableStateOf(TextFieldValue()) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("Invalid name") }


    LimpeanAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            NameTextField(
                value = nameValue,
                onValueChange = {
                    nameValue = it
                    isError = !isValidName(it.text)
                },
                isError = isError,
                errorMessage = errorMessage
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Submit")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean = false,
    errorMessage: String = "Invalid name",
    label: String = "Name"
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            // Perform validation here (e.g., check if newValue is a valid name)
            if (isValidName(newValue.text)) {
                onValueChange(newValue)
            }
        },
        label = { Text(label) },
        isError = isError,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) Color.Red else Color.Gray,
            unfocusedBorderColor = if (isError) Color.Red else Color.Gray
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Perform any action when the Done button is clicked (e.g., submit the form)
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )

    // Display an error message if isError is true
    if (isError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}