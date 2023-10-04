package br.senai.sp.jandira.limpeanapp.authentication.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.example.compose.LimpeanAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu() {
    var expanded by remember{
        mutableStateOf(false)
    }
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }


    var genders = arrayOf("Masculino","Feminino","Prefiro não informar","Outro")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(16.dp),
    ) {
        TextField(
            value = genders[selectedItemIndex],
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
                genders.forEachIndexed { index, item ->
                    DropdownMenuItem(text = { Text(text = item) }, onClick = {
                        selectedItemIndex = index
                        expanded = false
                    })
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    LimpeanAppTheme {
        Menu()
    }

}