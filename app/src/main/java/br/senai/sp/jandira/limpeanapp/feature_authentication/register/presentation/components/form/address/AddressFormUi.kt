package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import com.example.compose.LimpeanAppTheme

//= Modifier
//.fillMaxHeight( 0.7f)
//.padding(20.dp)
@Composable
fun AddressFormUi(
    state : AddressFormState,
    onChangeNumber : (String) -> Unit,
    onChangeCep : (String) -> Unit,
    modifier : Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Cep")
            },
            value = state.cep,
            onValueChange = {
                onChangeCep(it)
            }
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.label_logradouro))},
            value = state.logradouro,
            onValueChange = {}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),

            label = { Text(text = stringResource(R.string.label_bairro))},
            readOnly = true,
                value = state.bairro,
                onValueChange = {}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.label_estado))},
            readOnly = true,
            value = state.estado ,
            onValueChange = {}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.label_cidade))},
            readOnly = true,
            value = state.cidade,
            onValueChange = {}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(id = R.string.label_numero))},
            value = state.numero,
            onValueChange = {
                onChangeNumber(it)
            }
        )
    }

}


@Preview
@Composable
fun AddressFormUiPreview() {
    LimpeanAppTheme {
        var cep by remember {
            mutableStateOf("")
        }
        var numero by remember {
            mutableStateOf("")
        }

        var state by remember {
            mutableStateOf(
                AddressFormState(
                    cep,
                    "",
                    "",
                    "",
                    "",
                    numero,
                    false,
                )
            )
        }
        AddressFormUi(
            state = state,
            onChangeCep = {
                cep = it
            },
            onChangeNumber = {
                numero = it
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}