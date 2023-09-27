package br.senai.sp.jandira.limpeanapp.telas.componentes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun FotoDePerfil() {

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
    Box {
        Card(
            modifier = Modifier.size(140.dp),
            shape = CircleShape,
        ) {
            Image(
                painter = if(photoUri == null) painterResource(id = R.drawable.profile) else painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    launcher.launch("image/png")
                    var message = "nada"
                    Log.i(
                        "ds2m",
                        "URI: ${photoUri?.path ?: message}"
                    )
                },
            painter = painterResource(
                id = R.drawable.add_a_photo),
            contentDescription = null,
        )


    }
    Text(
        text = "Escolha uma foto de perfil",
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(400),
            color = Color(0xFF53575A),
        )
    )

}

@Preview
@Composable
fun FotoDePerfilPreview() {
    FotoDePerfil()
}
