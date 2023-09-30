package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import ViewModelCep
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.componentes.Button
import br.senai.sp.jandira.limpeanapp.telas.componentes.SelectionMenu
import com.dsc.form_builder.SelectState
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary


@Composable
fun AddressForm(
      viewModelCep: ViewModelCep = viewModel(),
) {


      val uiState = viewModelCep.uiState
      val formState = uiState.addressForm
      val cepState: TextFieldState = formState.getState("Cep")
      val numberState : TextFieldState = formState.getState("Número")


      Column {
                  //Para definir o tipo de casa caso o formulario seja do Usuário Cliente//            if(viewModelCep.isClient()){
//                  val typesHouse = // listaDeTiposDeCasa
//                  val selectedTypeHouseState : SelectState = formState.getState("TypeHouse")
//                  SelectionMenu(
//                        placeHolder = stringResource(R.string.label_tipo_casa),
//                        options = typesHouse,
//                        onSelectedOption = {
//                              selectedTypeHouseState.select(it)
//                        }
//                  )
//            }
            OutlinedTextField(
                  label = {
                        Text(text = cepState.name)
                  },
                  value = cepState.value,
                  onValueChange = {
                        cepState.change(it)
                        if (it.length == 8) {
                              viewModelCep.fetchCep(it)
                        }
                  }
            )

            if (uiState.isFetchingApi) {
                  CircularProgressIndicator()
            }

            if(uiState.showResultApi){
                  val viaCepAddress = uiState.viaCepAddress
                  OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_logradouro))},
                        value = viaCepAddress!!.logradouro,
                        onValueChange = {}
                  )
                  OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_bairro))},
                        readOnly = true,
                        value = viaCepAddress.bairro,
                        onValueChange = {}
                  )
                  OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_estado))},
                        readOnly = true,
                        value = viaCepAddress.estado ,
                        onValueChange = {}
                  )
                  OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_cidade))},
                        readOnly = true,
                        value = viaCepAddress.cidade,
                        onValueChange = {}
                  )
            }

            OutlinedTextField(
                  label = { Text(stringResource(id = R.string.label_numero))},
                  value = numberState.value,
                  onValueChange = {
                        numberState.change(it)
                  }
            )
      }

}


@Preview
@Composable
fun AddressPreview() {
      val route = "address"
      val viewModelCep = viewModel<ViewModelCep>()
      val addressForm = viewModelCep.uiState.addressForm
      LimpeanAppTheme {
            RegisterScreen(
                  title = "Teste",
                  form = {
                        AddressForm(viewModelCep)
                  } ,
                  nameButton = "Teste Adress"
            ) {


            }

      }
}


