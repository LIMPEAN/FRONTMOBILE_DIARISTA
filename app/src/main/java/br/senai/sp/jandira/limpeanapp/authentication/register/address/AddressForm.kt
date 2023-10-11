package br.senai.sp.jandira.limpeanapp.authentication.register.address

import CepViewModel
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterAddressScreen
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme


@Composable
fun AddressForm(
      cepViewModel: CepViewModel = viewModel(),
) {


      val uiState = cepViewModel.uiState
      val formState = uiState.addressForm
      val cepState: TextFieldState = formState.getState("cep")
      val numberState : TextFieldState = formState.getState("numero")


      Column {
            OutlinedTextField(
                  label = {
                        Text(text = "Cep")
                  },
                  value = cepState.value,
                  onValueChange = {
                        cepState.change(it)
                        if (it.length == 8) {
                              cepViewModel.fetchCep(it)
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

      LimpeanAppTheme {
            Box(contentAlignment = Alignment.Center) {

                  AddressForm()
            }
      }
}


