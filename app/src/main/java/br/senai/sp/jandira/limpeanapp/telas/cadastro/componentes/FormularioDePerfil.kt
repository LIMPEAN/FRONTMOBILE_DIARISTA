package br.senai.sp.jandira.limpeanapp.telas.cadastro.componentes

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import br.senai.sp.jandira.limpeanapp.telas.componentes.FotoDePerfil
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
    }


}


@Preview(showSystemUi = true)
@Composable
fun FormularioDePerfilPreview() {
    LimpeanAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            FormularioDePerfil()
        }
    }
}
