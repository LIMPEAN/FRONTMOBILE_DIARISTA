package br.senai.sp.jandira.limpeanapp.ui.welcome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.domain.UserType
import com.example.compose.LimpeanAppTheme

@Composable
fun SelectUserType(
    userTypes: List<UserType>,
    selectedUserType: UserType,
    onSelectedChange: (UserType) -> Unit
) {
    val names = userTypes.map { it.portugueseName }
    val selectedName = selectedUserType.portugueseName

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        textComLinhas(texto = "Iniciar como")
        ButtonGroup(
            options = names,
            selectedOption = selectedName,
            onOptionSelected = { selectedName ->
                val selectedType = userTypes.find { it.portugueseName == selectedName }
                selectedType?.let { onSelectedChange(it) }
            }
        )
    }
}

@Preview
@Composable
fun SelectUserTypePreview() {
    val names = listOf<String>("Diarista", "Cliente")
    var  selected by remember {
        mutableStateOf("Diarista")
    }
    LimpeanAppTheme {
    }
}