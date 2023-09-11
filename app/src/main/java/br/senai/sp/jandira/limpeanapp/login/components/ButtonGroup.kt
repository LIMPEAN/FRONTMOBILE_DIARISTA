package br.senai.sp.jandira.limpeanapp.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_secondary
import com.example.compose.md_theme_light_primary


@Composable
fun ButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.width(330.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
                    contentColor = if (isSelected) {
                        md_theme_light_primary// Text color for the selected button
                    } else {
                        Color.White // Text color for unselected buttons
                    }
                ),
                modifier = Modifier
                    .width(160.dp)
                    .padding(16.dp)
                   ,
                shape = RoundedCornerShape(8.dp)

            ) {
                Text(
                    text = option,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = poopins,
                        fontWeight = FontWeight(400),
                        color = if(isSelected){md_theme_light_primary} else{Color.White },
                        letterSpacing = 0.2.sp,
                    )
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

    LimpeanAppTheme {
        Column {
            ButtonGroup(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { newOption ->
                    selectedOption = newOption
                }
            )
            ButtonGroup(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { newOption ->
                    selectedOption = newOption
                }
            )
        }


    }

}