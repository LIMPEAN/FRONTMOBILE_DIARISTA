package br.senai.sp.jandira.limpeanapp.registration

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.components.InputText
import br.senai.sp.jandira.limpeanapp.login.LoginEvent
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    userType: String,
    onEvent: (RegistrationEvent) -> Unit
) {
    Column {
        // Top section with styled label
        Text(
            text = "Cadastro de $userType",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF3147F5))
                .padding(16.dp), // Set background color
            style = TextStyle(
                fontFamily = poopins,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White // Set text color
            )
        )

        InputText(
            label = "Name",
            state = state.name,
            keyboardType = KeyboardType.Text,
            onTyping = { newName ->
                onEvent(RegistrationEvent.NameChanged(newName))
            }
        )

        InputText(
            label = "CPF",
            keyboardType = KeyboardType.Number,
            state = state.cpf,
            onTyping = { newCPF ->
                onEvent(RegistrationEvent.CPFChanged(newCPF))
            }
        )

        InputText(
            label = "RG",
            keyboardType = KeyboardType.Number,
            state = state.rg,
            onTyping = { newRG ->
                onEvent(RegistrationEvent.RGChanged(newRG))
            }
        )

        InputText(
            label = "Telephone",
            keyboardType = KeyboardType.Phone,
            state = state.telephone,
            onTyping = { newTelephone ->
                onEvent(RegistrationEvent.TelephoneChanged(newTelephone))
            }
        )

        InputText(
            label = "Date of Birth",
            keyboardType = KeyboardType.Number,
            state = state.dateOfBirth,
            onTyping = { newDateOfBirth ->
                onEvent(RegistrationEvent.DateOfBirthChanged(newDateOfBirth))
            }
        )

        Text("Gender")
        DropdownMenu(
            expanded = false,
            onDismissRequest = { /* Dismiss the dropdown if needed */ }
        ) {
            listOf("Male", "Female", "Other").forEach { gender ->
                DropdownMenuItem(
                    text = { Text(text = gender) },
                    onClick = {
                        onEvent(RegistrationEvent.GenderSelected(gender))
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {

    LimpeanAppTheme {
        RegistrationScreen(
            onEvent = {},
            state = RegistrationState(),
            userType = "Diarista"
            )
    }
}