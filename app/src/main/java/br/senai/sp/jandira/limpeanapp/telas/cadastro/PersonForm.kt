package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes.BotaoDeCadastro
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import br.senai.sp.jandira.limpeanapp.telas.componentes.SelectionMenu
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme

@Composable
fun PersonForm(
    viewModel : IntegracaoDeCadastro = viewModel(factory = IntegracaoDeCadastro.fazerIntegracaoComApi)
) {

    val formState = remember { viewModel.personFormState }

    val fieldStates = listOf<TextFieldState>(
        formState.getState("Nome"),
        formState.getState("Cpf"),
        formState.getState("Telefone"),
        formState.getState("Data de Nascimento"),
    )
    val genderState: ChoiceState = formState.getState("Genders")
    val genders = viewModel.generosState.map {  it.etiqueta }

    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.dados_pessoais))
        fieldStates.map {
            CaixaDeTexto(it)
        }
        SelectionMenu(placeHolder = stringResource(R.string.informe_genero), options = genders, onSelectedOption = {genderState.change(it)})

        BotaoDeCadastro(nomeDaAcao = "Validar") {
            if(formState.validate()){
                Toast.makeText(context, "Campo válido", Toast.LENGTH_SHORT).show()
            }
        }
    }




}

@Preview
@Composable
fun PersonFormPreview() {
    LimpeanAppTheme {
        PersonForm()
    }
}