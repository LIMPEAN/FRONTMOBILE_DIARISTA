package br.senai.sp.jandira.limpeanapp.telas.inicio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import com.example.compose.LimpeanAppTheme

@Composable
fun SelectUserType(
    userTypes: List<TipoDeUsuario>,
    selectedUserType: TipoDeUsuario,
    onSelectedChange: (TipoDeUsuario) -> Unit
) {
    val names = userTypes.map { it.nomeEmPortugues }
    val selectedName = selectedUserType.nomeEmPortugues

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        textComLinhas(texto = "Iniciar como")
        ButtonGroup(
            options = names,
            selectedOption = selectedName,
            onOptionSelected = { selectedName ->
                val selectedType = userTypes.find { it.nomeEmPortugues == selectedName }
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