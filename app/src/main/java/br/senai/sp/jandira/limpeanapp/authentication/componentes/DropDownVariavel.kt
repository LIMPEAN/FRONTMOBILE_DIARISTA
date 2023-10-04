package br.senai.sp.jandira.limpeanapp.authentication.componentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Calling the composable function
            // to display element and its contents
            PhoneNumberTypeMenu()
        }
    }
}


// Creating a composable function
// to create an Outlined Text Field
// Calling this function as content
// in the above function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberTypeMenu() {
    val types = listOf("Fijo", "Móvil", "Trabajo", "Otro")
    val default = 0

    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(types[default]) } // (1)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded // (2)
        },
        modifier = Modifier.width(150.dp)
    ) {
        TextField(
            readOnly = true, // (3)
            value = selectedType, // (4)
            onValueChange = { },
            label = { Text("Tipo") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon( // (5)
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = true
            }
        ) {
                DropdownMenuItem(
                    onClick = {
                        selectedType = types[0]
                        expanded = true
                    },
                    text = {
                        Text(text = types[0])
                    }
                )
            }
        }
    }



// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneNumberTypeMenu()
}

