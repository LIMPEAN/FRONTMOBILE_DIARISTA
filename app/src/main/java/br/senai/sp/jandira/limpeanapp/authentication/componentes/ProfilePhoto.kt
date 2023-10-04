package br.senai.sp.jandira.limpeanapp.authentication.componentes

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.authentication.register.SignInViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dsc.form_builder.TextFieldState

@Composable
fun ProfilePhoto(
    onAddPhoto : () -> Unit,
    viewModel : SignInViewModel = viewModel(
        factory = SignInViewModel.fazerIntegracaoComApi
    ),
    photo: @Composable () -> Unit,
    isPhotoSelected : Boolean = false
) {


    Box {
        Card(
            modifier = Modifier.size(140.dp),
            shape = CircleShape,
        ) {
            photo()

        }
        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    onAddPhoto()
                },
            painter = painterResource(
                id = R.drawable.add_a_photo),
            contentDescription = null,
        )

    }
    if(!isPhotoSelected){
        Text(
            text = stringResource(R.string.description_profile_photo),
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = Color(0xFF53575A),
            )
        )
    }


}

@Preview
@Composable
fun FotoDePerfilPreview() {

    val viewModel : SignInViewModel = viewModel(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
    val userForm = remember {viewModel.userForm}

    val photoUri : TextFieldState = userForm.getState("photoUri")

    var selectedPhotoUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedPhotoUri = uri
            photoUri.change(uri.toString())
        }
    )
    val asyncPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedPhotoUri)
                    .build(),
        contentScale = ContentScale.Crop
    )


    LazyColumn{
        item {
            ProfilePhoto(
                onAddPhoto = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts
                                .PickVisualMedia
                                .ImageOnly
                        )
                    )
                },
                photo = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = if(selectedPhotoUri != null){
                            asyncPainter
                        } else {
                            painterResource(id = R.drawable.profile)
                        },
                        contentDescription = null
                    )
                }
            )
        }
    }

}

