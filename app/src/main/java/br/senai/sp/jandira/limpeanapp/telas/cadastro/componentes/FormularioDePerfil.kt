package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.telas.componentes.CaixaDeTexto
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDePerfil() {

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
    var nomeState by remember {mutableStateOf("")}
    var cpfState by remember { mutableStateOf("") }
    var rgState by remember {mutableStateOf("")}
    var telefoneState by remember {mutableStateOf("") }
    var dataNascimentoState by remember {mutableStateOf(false)}
    var generoState by remember {mutableStateOf("")}
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        CaixaDeTexto(etiqueta = "Digite seu nome", estado = nomeState, aoDigitar = { nomeState = it})
        CaixaDeTexto(etiqueta = "Seu CPF", estado = cpfState, aoDigitar = { cpfState = it})
        CaixaDeTexto(etiqueta = "Seu RG", estado = rgState, aoDigitar = { rgState = it} )
        CaixaDeTexto(etiqueta = "Telefone", estado = telefoneState , aoDigitar = { telefoneState = it })


        Spacer(modifier = Modifier.height(16.dp))
    }


}


@Preview
@Composable
fun FormularioDePerfilPreview() {
    LimpeanAppTheme {
        FormularioDePerfil()
    }
}
fun saveUser(
    userName: String,
    phone: String,
    email: String,
    password: String,
    isOver18: Boolean,
    profilePhotoUri: String,
    context: Context
) {

}
