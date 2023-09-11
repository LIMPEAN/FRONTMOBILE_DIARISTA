package br.senai.sp.jandira.limpeanapp.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.login.InMemoryUserTypeRepository
import br.senai.sp.jandira.limpeanapp.login.UserType
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun SelectTypeUser(
    userTypes : List<UserType>,
    selected : UserType,
    selectedChange : (UserType) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (type in userTypes) {
            RadioButton(
                selected = selected == type,
                onClick = {
                    selectedChange(type)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.White
                )
            )
            Text(
                text = type.portugueseName, // Provide the label for the user type
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectTypeUserPreview() {
    val userTypes = InMemoryUserTypeRepository.getAll()
    var selectedUserType by remember {
        mutableStateOf(userTypes[0])
    }
    LimpeanAppTheme {
        Column (Modifier.background(md_theme_light_primary)){
            SelectTypeUser(userTypes = userTypes, selected = selectedUserType) {
                selectedUserType = it
            }
        }

    }
}