package br.senai.sp.jandira.limpeanapp.telas.cadastro

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory


@Composable
fun CadastroRapido(){
    val viewModel = viewModel<IntegracaoDeCadastro>()
    val state = viewModel.cadastroState

    Column {
        Text(text = "Cadastro Rápido")
        Button(onClick = {
            viewModel.cadastrarDiarista(null)
        }) {
            Text(text = "Cadastrar")
            if(state.status != null){
                Text(text = state.status)
            }

        }
    }
}


@Preview
@Composable
fun CadastroRapidoTeste() {
    CadastroRapido()
}
