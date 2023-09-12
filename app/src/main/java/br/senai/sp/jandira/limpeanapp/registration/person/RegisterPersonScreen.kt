package br.senai.sp.jandira.limpeanapp.registration.person

import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.components.InputText
import br.senai.sp.jandira.limpeanapp.login.InMemoryUserTypeRepository
import br.senai.sp.jandira.limpeanapp.login.UserType
import br.senai.sp.jandira.limpeanapp.registration.RegistrationEvent
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun RegisterPersonScreen(
    userType: UserType,
    state: RegisterPersonState,
    onEvent: (PersonEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Enter your Personal data here")

        OutlinedTextField(
            value = state.name,
            onValueChange = { newName ->
                onEvent(PersonEvent.NameChanged(newName))
            },
            label = { Text("Name") }
        )

        OutlinedTextField(
            value = state.cpf,
            onValueChange = { newCPF ->
                onEvent(PersonEvent.CPFChanged(newCPF))
            },
            label = { Text("CPF") }
        )

        OutlinedTextField(
            value = state.id,
            onValueChange = { newID ->
                onEvent(PersonEvent.IDChanged(newID))
            },
            label = { Text("ID") }
        )

        OutlinedTextField(
            value = state.telephone,
            onValueChange = { newTelephone ->
                onEvent(PersonEvent.TelephoneChanged(newTelephone))
            },
            label = { Text("Telephone") }
        )

        DatePicker(
            selectedDate = state.dateOfBirth,
            onDateSelected = { newDateOfBirth ->
                onEvent(PersonEvent.DateOfBirthChanged(newDateOfBirth))
            },
            label = { Text("Date of Birth") }
        )

        // Gender selection
        Text("Gender")
        RadioGroup(
            options = listOf("Male", "Female", "Other"),
            selectedOption = state.gender,
            onOptionSelected = { newGender ->
                onEvent(PersonEvent.GenderSelected(newGender))
            }
        )

        Button(
            onClick = { onEvent(PersonEvent.SubmitClicked) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {

    LimpeanAppTheme {
        val state = RegisterPersonState()
        RegisterPersonScreen(state = state, onEvent = {}, userType = InMemoryUserTypeRepository.getAll()[0])

    }
}