package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import br.senai.sp.jandira.limpeanapp.dados.User
import br.senai.sp.jandira.limpeanapp.dados.Usuario
import br.senai.sp.jandira.limpeanapp.regras.UserType
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import br.senai.sp.jandira.limpeanapp.telas.componentes.FotoDePerfil
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDePerfil(
    salvarPerfil : (Perfil) -> Unit
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
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember {mutableStateOf("")}
    var senhaRepetidaState by remember {mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FotoDePerfil()
        CaixaDeTexto(etiqueta = "Fale sobre você", estado = biografiaState, aoDigitar = { biografiaState = it})
        CaixaDeTexto(etiqueta = "Seu melhor email", estado = emailState, aoDigitar = { emailState = it})
        CaixaDeTexto(etiqueta = "Senha", estado = senhaState, aoDigitar = { senhaState = it})
        CaixaDeTexto(etiqueta = "Repita sua senha", estado = senhaRepetidaState , aoDigitar = { senhaRepetidaState = it })


        Spacer(modifier = Modifier.height(16.dp))


        BotaoDeCadastro(nomeDaAcao = "Próxima") {
            val perfil  = Perfil(
                photoUri,
                biografiaState,
                emailState,
                senhaState
            )
            salvarPerfil(perfil)
        }
    }


}


@Preview(showSystemUi = true)
@Composable
fun FormularioDePerfilPreview() {
    LimpeanAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            FormularioDePerfil(){
                Log.i("PERFIL-SALVO", it.toString())
            }
        }
    }
}
data class Perfil (
    val fotoDePerfil : Uri? = null,
    val biografia : String? = null,
    val email : String? = null,
    val senha : String? = null
)