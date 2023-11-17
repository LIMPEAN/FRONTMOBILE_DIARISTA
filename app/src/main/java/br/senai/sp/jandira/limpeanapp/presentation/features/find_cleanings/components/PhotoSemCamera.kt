package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

object ProfileFormBuilder{
    fun build() : FormState<TextFieldState>{
        return FormState(listOf(
            addProfilePhotoUri()
        ))
    }

    fun addProfilePhotoUri(): ChoiceState {
        return ChoiceState(
            name = "photoUri",
            validators = listOf(
                Validators.Required("É obrigatório escolher uma foto!")
            )
        )
    }

}

@Composable
fun PhotoSemCamera(
    onAddPhoto: () -> Unit,
//    viewModel: SignInViewModel = viewModel(
//        factory = SignInViewModel.fazerIntegracaoComApi
//    ),
    photo: @Composable () -> Unit,
    isPhotoSelected: Boolean = false
) {


    Box {
        Card(
            modifier = Modifier.size(140.dp),
            shape = CircleShape,
        ) {
            photo()

        }
//        Image(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .clickable {
//                    onAddPhoto()
//                },
//            painter = painterResource(
//                id = R.drawable.add_a_photo
//            ),
//            contentDescription = null,
//        )

    }
    if (!isPhotoSelected) {

    }
}

class SignInViewModel (): ViewModel(){
    var userForm : FormState<TextFieldState> by mutableStateOf(ProfileFormBuilder.build())
        private set

    var photoUri by mutableStateOf<Uri?>(null)

}

@Preview
@Composable
fun FotoDePerfilPreview() {

    val viewModel : SignInViewModel = viewModel(

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
            PhotoSemCamera(
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
