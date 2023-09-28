package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import ViewModelCep
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators


@Composable
fun FormularioDeEndereco(
      cep: ViewModelCep = viewModel()
) {

      val cepViewModel: ViewModelCep = viewModel()
      var cep by remember { mutableStateOf("") }
      var endereco by remember { mutableStateOf("") }

      val uiState = cepViewModel.cepState
      Column {


            Text(text = "Seu endereco ....")
      
            val cepState = TextFieldState(
                  name = "cep",
                  initial = ""
            )

            CaixaDeTexto(state = cepState)
            BotaoDeCadastro(nomeDaAcao = "Verificar CEP") {
                  var teste = cepViewModel.fetchCep(cep)
                  Log.i("TESTE", "${teste}")
                  cep = teste.toString()
                  

            }
            Text(text = cepViewModel.cepState.toString())
            Text(text = endereco)

      }
}

@Preview(showSystemUi = true)
@Composable
fun FormularioDeEnderecoPreview() {
      FormularioDeEndereco (
            cep = ViewModelCep()
      )
}




