package br.senai.sp.jandira.limpeanapp.authentication.register.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.authentication.register.RegisterScreen
import br.senai.sp.jandira.limpeanapp.authentication.register.SignInViewModel
import br.senai.sp.jandira.limpeanapp.authentication.componentes.ProfilePhoto
import br.senai.sp.jandira.limpeanapp.authentication.componentes.PasswordField
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dsc.form_builder.TextFieldState
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_error


data class UserFormField(
    val name : String,
    val state : TextFieldState
)


@SuppressLint("ShowToast")
@Composable
fun UserForm(
    signInViewModel: SignInViewModel = viewModel(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
) {
    val context = LocalContext.current


    val userForm = remember { signInViewModel.userForm }
    val userFormFields = listOf(
        UserFormField(
            name = "Fale sobre sua biografia",
            state = userForm.getState("biography")
        ),
        UserFormField(
            name = "Email",
            state = userForm.getState("email")
        ),
        UserFormField(
            name = "Senha",
            state = userForm.getState("password")
        ),
        UserFormField(
            name = "Repita sua senha",
            state = userForm.getState("repeatedPassword")
        )
    )


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
        },
        isPhotoSelected = selectedPhotoUri == null
    )
        userFormFields.map {
            if(isPasswordField(it)){
                PasswordField(
                    etiqueta = it.name,
                    estado = it.state.value,
                    aoDigitar = {newValue -> it.state.change(newValue)})

            } else{
                OutlinedTextField(
                    label = { Text(it.name)},
                    value = it.state.value,
                    onValueChange = {newValue -> it.state.change(newValue)}
                )
            }
            if(it.state.hasError){
                Text(
                    text = it.state.errorMessage,
                    color = md_theme_light_error
                )
            }
        }

}

fun isPasswordField(formField: UserFormField) : Boolean = formField.name == "Senha" || formField.name == "Repita sua senha"

@Preview(showSystemUi = true)
@Composable
fun UserFormPreview() {
    val viewModel = viewModel<SignInViewModel>(
        factory = SignInViewModel.fazerIntegracaoComApi
    )
    LimpeanAppTheme {
        val context = LocalContext.current
        RegisterScreen(
            title = "Crie seu Perfil",
            form = {
                UserForm(viewModel)
            },
            nameButton = "Próximo"
        ) {
            viewModel.validateUserForm()
        }

    }
}