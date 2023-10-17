package br.senai.sp.jandira.limpeanapp.auth.presentation.welcome.components

//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
//import com.example.compose.LimpeanAppTheme
//
//@Composable
//fun SelectUserType(
//    userTypes: List<TipoDeUsuario>,
//    selectedUserType: TipoDeUsuario,
//    onSelectedChange: (TipoDeUsuario) -> Unit
//) {
//    val names = userTypes.map { it.nomeEmPortugues }
//    val selectedName = selectedUserType.nomeEmPortugues
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        textComLinhas(texto = "Iniciar como")
//        Spacer(Modifier.height(8.dp))
//        ButtonGroup(
//            options = names,
//            selectedOption = selectedName,
//            onOptionSelected = { selectedName ->
//                val selectedType = userTypes.find { it.nomeEmPortugues == selectedName }
//                selectedType?.let { onSelectedChange(it) }
//            }
//        )
//    }
//}
//
//@Preview
//@Composable
//fun SelectUserTypePreview() {
//
//    var  selected by remember {
//        mutableStateOf(TipoDeUsuario.pegaDiarista())
//    }
//    var options = TipoDeUsuario.listar()
//    LimpeanAppTheme {
//        SelectUserType(userTypes = options, selectedUserType = selected, onSelectedChange = {
//            selected = it
//        } )
//
//    }
//}