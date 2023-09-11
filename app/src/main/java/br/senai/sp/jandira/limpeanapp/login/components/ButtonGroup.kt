package br.senai.sp.jandira.limpeanapp.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_dark_secondary
import com.example.compose.md_theme_light_primary


@Composable
fun ButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Button(
                onClick = {
                    if (!isSelected) {
                        onOptionSelected(option)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) {
                       Color.White // Color for the selected button
                    } else {
                        md_theme_light_primary // Color for unselected buttons
                    },
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = option,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        color = if (option == selectedOption) {
                            Color.Black // Text color for the selected button
                        } else {
                            Color.White // Text color for unselected buttons
                        }
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ButtonGroupPreview() {
    var selectedOption by remember { mutableStateOf("Diarista") }
    val options = listOf("Diarista", "Cliente")

    ButtonGroup(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { newOption ->
            selectedOption = newOption
        }
    )
}