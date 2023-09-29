package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.dados.modelos.Genero
import br.senai.sp.jandira.limpeanapp.dados.modelos.Telefone
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.telas.componentes.Button
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTextoSemDropDown
import br.senai.sp.jandira.limpeanapp.telas.componentes.SelectionMenu
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onPrimary
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FormularioDePessoa(
    salvarDados : (Pessoa) -> Unit
) {

    var dadosDePessoa by remember {
        mutableStateOf(Pessoa())
    }

    var nomeState by remember {
        mutableStateOf("")
    }

    var cpfState by remember {
        mutableStateOf("")
    }

    var telefoneState by remember {
        mutableStateOf("")
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CaixaDeTextoSemDropDown(
            etiqueta = "Nome",
            estado = nomeState,
            aoDigitar = { nomeState = it},

        )
        Spacer(modifier = Modifier.height(25.dp))
        CaixaDeTextoSemDropDown(
            etiqueta = "Cpf",
            estado = cpfState,
            aoDigitar = { cpfState = it }
        )
        Spacer(modifier = Modifier.height(25.dp))
        CaixaDeTextoSemDropDown(
            etiqueta = "Telefone",
            estado = telefoneState,
            aoDigitar = { telefoneState = it },

        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "Data Nascimento", onValueChange = {},
            modifier = Modifier
                .width(368.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = md_theme_dark_onPrimary,
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            shape = RoundedCornerShape(size = 40.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        SelectionMenu(
            placeHolder = "Informe seu gênero",
            options = listOf("Masculino", "Feminino", "Outros", "Prefiro não informar"),
            onSelectedOption = {})

        Spacer(modifier = Modifier.height(150.dp))

        Button("Continuar", action = {val testeDePessoa = Pessoa(
            nome =  "Felipe",
            dataDeNascimento = LocalDate.now(),
            genero = Genero.MASCULINO,
            cpf = "Meu cpf",
            telefone = Telefone(11, "92839485")
        )
            salvarDados(testeDePessoa)})
    }
}
data class Formulario (
    val nome: String = "",
    val dataDeNascimento: Date? = Date(),
    val genero: Genero? = null,
    val cpf: String = "",
    val telefone: String = "",
)


@Preview(showSystemUi = true)
@Composable
fun FormularioPreview() {
    LimpeanAppTheme {
        FormularioDePessoa(salvarDados = {
            Log.i("SALVO-COM-SUCESSO", it.toString())
        })
    }

}


