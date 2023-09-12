package br.senai.sp.jandira.limpeanapp.ui.signin.components

import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.domain.Gender
import br.senai.sp.jandira.limpeanapp.domain.Person
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
    var birthDate by remember { mutableStateOf(person.birthDate) }
    var gender by remember { mutableStateOf(person.gender) }
    var cpf by remember { mutableStateOf(person.cpf) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Name
        TextField(
            value = name,
            onValueChange = { newName ->
                name = newName
                onPersonChanged(
                    person.copy(name = newName)
                )
            },
            label = { Text("Name") }
        )

        // Birth Date
//        DatePicker(
//            selectedDate = birthDate,
//            onDateSelected = { newDate ->
//                birthDate = newDate
//                onPersonChanged(
//                    person.copy(birthDate = newDate)
//                )
//            },
//            label = { Text("Birth Date") }
//        )

        // Gender
        Text("Gender")
        DropdownMenu(
            expanded = false,
            onDismissRequest = { /* Dismiss the dropdown if needed */ }
        ) {
            Gender.values().forEach { g ->
                DropdownMenuItem(
                    onClick = {
                        gender = g
                        onPersonChanged(
                            person.copy(gender = g)
                        )
                    },
                    text = {Text(text = g.toString())}
                )
            }
        }

        // CPF
        TextField(
            value = cpf,
            onValueChange = { newCPF ->
                cpf = newCPF
                onPersonChanged(
                    person.copy(cpf = newCPF)
                )
            },
            label = { Text("CPF") }
        )
    }
}

@Preview
@Composable
fun PersonFormPreview() {
    LimpeanAppTheme {
        var person = Person("Felipe", Date(),Gender.values()[0],"")
        PersonForm(person = person, onPersonChanged = {
            person = it
        })
    }
}