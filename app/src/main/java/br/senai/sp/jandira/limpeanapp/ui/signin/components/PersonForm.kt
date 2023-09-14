package br.senai.sp.jandira.limpeanapp.ui.signin.components

import android.app.LocaleConfig
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.domain.Gender
import br.senai.sp.jandira.limpeanapp.domain.Person
import br.senai.sp.jandira.limpeanapp.domain.use_cases.ValidateName
import br.senai.sp.jandira.limpeanapp.ui.signin.SignInScreen
import br.senai.sp.jandira.limpeanapp.ui.signup.SignUpScreen
import com.example.compose.LimpeanAppTheme
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonForm(
    person: Person,
    onPersonChanged: (Person) -> Unit
) {
    var name by remember { mutableStateOf(person.name) }
    var cpf by remember { mutableStateOf(person.cpf) }
    var rg by remember { mutableStateOf(person.rg) }
    var dateOfBirth by remember { mutableStateOf(person.dateOfBirth) }
    var gender by remember { mutableStateOf(person.gender) }




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