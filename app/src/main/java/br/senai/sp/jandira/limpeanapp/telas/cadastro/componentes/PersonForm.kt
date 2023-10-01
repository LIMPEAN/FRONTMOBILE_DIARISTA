package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes



import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
import br.senai.sp.jandira.limpeanapp.telas.cadastro.SignInViewModel
import br.senai.sp.jandira.limpeanapp.telas.componentes.SelectionMenu
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_error


@Composable
fun PersonForm(
    signInViewModel: SignInViewModel = viewModel(
        factory = SignInViewModel.fazerIntegracaoComApi
    ),
    gendersName : List<String> = emptyList()
) {
    val uiState = signInViewModel.uiState
    val formState = uiState.personForm

    val nomeState: TextFieldState = formState.getState("name")
    val cpfState: TextFieldState = formState.getState("cpf")
    val telefoneState: TextFieldState = formState.getState("phone")
    val dataNascimentoState: TextFieldState = formState.getState("dateBirth")
    val generosState: ChoiceState = formState.getState("gender")


    OutlinedTextField(
        label = { Text(text = "Nome") },
        isError = nomeState.hasError,
        value = nomeState.value,
        onValueChange = { newValue ->
            nomeState.change(newValue)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        visualTransformation = signInViewModel.getVisualTransformation(nomeState.name)
            ?: VisualTransformation.None
    )

    if (nomeState.hasError) {
        Text(
            text = nomeState.errorMessage ,
            color = md_theme_light_error
        )
    }

    OutlinedTextField(
        label = { Text(text = "Cpf") },
        isError = cpfState.hasError,
        value = cpfState.value,
        onValueChange = { newValue ->
            cpfState.change(newValue)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = signInViewModel.getVisualTransformation(cpfState.name)
            ?: VisualTransformation.None
    )

    if (cpfState.hasError) {
        Text(
            text = cpfState.errorMessage ,
            color = md_theme_light_error
        )
    }

    OutlinedTextField(
        label = { Text(text = "Telefone") },
        isError = telefoneState.hasError,
        value = telefoneState.value,
        onValueChange = { newValue ->
            telefoneState.change(newValue)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType =  KeyboardType.Number
        ),
        visualTransformation = signInViewModel.getVisualTransformation(telefoneState.name)
            ?: VisualTransformation.None
    )

    if (telefoneState.hasError) {
        Text(
            text = telefoneState.errorMessage,
            color = md_theme_light_error
        )
    }

    OutlinedTextField(
        label = { Text(text = "Data de Nascimento") },
        isError = dataNascimentoState.hasError,
        value = dataNascimentoState.value,
        onValueChange = { newValue ->
            dataNascimentoState.change(newValue)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (dataNascimentoState.name != "Nome") KeyboardType.Number else KeyboardType.Text
        ),
        visualTransformation = signInViewModel.getVisualTransformation(dataNascimentoState.name)
            ?: VisualTransformation.None
    )

    if (dataNascimentoState.hasError) {
        Text(
            text = dataNascimentoState.errorMessage ,
            color = md_theme_light_error
        )
    }
    SelectionMenu(
        placeHolder = "Informe seu gênero",
        options = gendersName ,
        onSelectedOption = {
            signInViewModel.onSelectGender(it)
        }
    )
    if(generosState.hasError){
        Text(
            text = dataNascimentoState.errorMessage ,
            color = md_theme_light_error
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun PersonFormPreview() {

    val viewModel : SignInViewModel = viewModel(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
    val uiState = viewModel.uiState
    val context = LocalContext.current

    val gendersName = Genero.values().map { it.etiqueta }
    LimpeanAppTheme (useDarkTheme = false){
        RegisterScreen(
            title = "Dados Pessoais",
            form = { PersonForm(viewModel, gendersName) },
            nameButton = "Continuar"
        ) {
            if(uiState.personForm.validate()){
                Toast.makeText(context,"Dados Bons, ${uiState.personForm}",Toast.LENGTH_LONG).show()
            }
            viewModel.getPersonData()
        }
    }
}