package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario
<<<<<<< HEAD
import br.senai.sp.jandira.limpeanapp.telas.cadastro.RegisterScreen
=======
import br.senai.sp.jandira.limpeanapp.telas.cadastro.TelaDeCadastro
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
import br.senai.sp.jandira.limpeanapp.telas.componentes.Button
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTextoSemDropDown
import br.senai.sp.jandira.limpeanapp.telas.componentes.FotoDePerfil
import br.senai.sp.jandira.limpeanapp.telas.componentes.PasswordField
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDePerfil(
    salvarPerfil : (Perfil) -> Unit,
    tipoDeUsuario: TipoDeUsuario
) {

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        photoUri = it
    }
    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(photoUri)
            .build()
    )
    //Body

    var biografiaState by remember {mutableStateOf("")}
    var biografiaError by remember { mutableStateOf("") }
    var biografiaHasError by remember { mutableStateOf(false) }
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember {mutableStateOf("")}
    var senhaRepetidaState by remember {mutableStateOf("") }
    var mediaState by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FotoDePerfil()
        Spacer(modifier = Modifier.height(43.dp))
        Column {
            if(tipoDeUsuario.nomeEmPortugues == "Diarista"){
                CaixaDeTexto(
                    state = TextFieldState(
                        name = "media",
                        initial = ""
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            CaixaDeTextoSemDropDown(
                etiqueta = "Fale sobre você",
                estado = biografiaState,
                aoDigitar = { biografiaState = it}
            )
            Spacer(modifier = Modifier.height(20.dp))
            CaixaDeTextoSemDropDown(
                etiqueta = "Seu melhor email",
                estado = emailState,
                aoDigitar = { emailState = it}
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordField(
                etiqueta = "Senha",
                estado = senhaState,
                aoDigitar = { senhaState = it}
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordField(
                etiqueta = "Repita sua senha",
                estado = senhaRepetidaState ,
                aoDigitar = { senhaRepetidaState = it }
            )

            Spacer(modifier = Modifier.height(30.dp))


        }
        Button("Continuar", action = {val perfil  = Perfil(
            fotoDePerfil = photoUri,
            biografia = biografiaState,
            email = emailState,
            senha = senhaState,
            media = mediaState.toDouble()
        )
            salvarPerfil(perfil)})
    }


}

data class Perfil (
    val fotoDePerfil : Uri? = null,
    val biografia : String? = null,
    val email : String? = null,
    val senha : String? = null,
    val media : Double? = null
)

@Preview
@Composable
fun FormularioDePerfil() {
    val tipoDeUsuario = TipoDeUsuario.pegaCliente()
    LimpeanAppTheme {
<<<<<<< HEAD

=======
        TelaDeCadastro(titulo = "Crie seu Perfil") {
            FormularioDePerfil(
                tipoDeUsuario = tipoDeUsuario,
                salvarPerfil = {
                    Log.i("PERFIL-CLIENTE",it.toString())
                }
            )
        }
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
    }

}

@Preview
@Composable
fun FormularioDePerfilDeDiarista() {
    val tipoDeUsuario = TipoDeUsuario.pegaDiarista()
    LimpeanAppTheme {
<<<<<<< HEAD

=======
        TelaDeCadastro(titulo = "Perfil") {
            FormularioDePerfil(
                tipoDeUsuario = tipoDeUsuario,
                salvarPerfil = {
                    Log.i("PERFIL-DIARISTA",it.toString())
                }
            )
        }
>>>>>>> b32abc5b2ab22d8161797d2c56321a4c569a36e1
    }

}