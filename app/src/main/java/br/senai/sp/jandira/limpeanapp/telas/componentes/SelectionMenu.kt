package br.senai.sp.jandira.limpeanapp.telas.componentes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onTertiaryContainer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionMenu(
    placeHolder : String,
    options : List<String>,
    onSelectedOption : (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        },
        modifier = Modifier.width(368.dp)
    ) {
        TextField (
            value = selected,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon (expanded = isExpanded)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF19161D),
                        letterSpacing = 0.2.sp,
                    )
                )
            },
            colors = ExposedDropdownMenuDefaults .textFieldColors(
            ),
            modifier = Modifier.menuAnchor(),
            shape = RoundedCornerShape(size = 40.dp)
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            modifier = Modifier.background(Color.White)
        ) {
            options.map {
                DropdownMenuItem(
                    text = { Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF19161D),
                            letterSpacing = 0.2.sp,
                        )
                    ) },
                    onClick = {
                        selected = it
                        isExpanded = false
                        onSelectedOption(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectionMenuPreview() {
    val genders = Genero.values()
    val options = genders.map { it.name.lowercase().replaceFirstChar { letter -> letter.uppercase() } }
    LimpeanAppTheme {
        SelectionMenu(
            placeHolder = "Selecione seu Genero",
            options = options
        ){
            Log.i("ESCOLHIDO", it)
        }
    }
}