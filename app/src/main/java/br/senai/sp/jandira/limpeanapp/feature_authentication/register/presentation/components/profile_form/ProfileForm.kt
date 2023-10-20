package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.limpeanapp.core.presentation.components.textfield.MyTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.RegisterEvent
import com.example.compose.LimpeanAppTheme




sealed class TextFieldVariant {
    object Password : TextFieldVariant()
    object Date : TextFieldVariant()
    object Normal : TextFieldVariant()
}

sealed class LabelName(name: String) {

}
@Composable
fun ProfileForm(
    profilePhoto : @Composable ()-> Unit,
    state : ProfileFormState,
    repeatedPassword: String,
    onEvent: (RegisterEvent)-> Unit
) {

    val profileProperties = listOf(
        ProfileItem.Name to state.name,
        ProfileItem.Cpf to state.cpf,
        ProfileItem.Phone to state.phone,
        ProfileItem.Email to state.email,
        ProfileItem.Password to state.password,
        ProfileItem.RepeatedPassword to state.repeatedPassword,
        ProfileItem.DateOfBirth to state.dateOfbirth
    )



    profilePhoto()

    Column(Modifier.fillMaxSize()) {
        profileProperties.map { (profileItem, property) ->
            val variant = if (profileItem == ProfileItem.Password || profileItem == ProfileItem.RepeatedPassword) {
                TextFieldVariant.Password
            } else {
                TextFieldVariant.Normal
            }
            MyTextField(
                state = property,
                nameLabel = profileItem.name,
                variant = variant,
                onValueChange = { newValue ->
                    when (profileItem) {
                        ProfileItem.Name -> onEvent(RegisterEvent.NameChanged(newValue))
                        ProfileItem.Cpf -> onEvent(RegisterEvent.CpfChanged(newValue))
                        ProfileItem.Phone -> onEvent(RegisterEvent.PhoneChanged(newValue))
                        ProfileItem.Email -> onEvent(RegisterEvent.EmailChanged(newValue))
                        ProfileItem.Password -> onEvent(RegisterEvent.PasswordChanged(newValue))
                        ProfileItem.RepeatedPassword -> onEvent(RegisterEvent.RepeatedPasswordChanged(newValue))
                        ProfileItem.DateOfBirth -> onEvent(RegisterEvent.DateOfBirthChanged(newValue))
                    }
                }
            )
        }
    }




}
data class ProfileFormState(
    val name : String = "",
    val cpf : String = "",
    val phone: String = "",
    val email : String = "",
    val password: String = "",
    val repeatedPassword : String = "",
    val dateOfbirth : String = "",
    val defineAveragePrice: Boolean = false,
    val defineBiography : Boolean = false,
    val averagePrice: Double = 0.0,
    val biography: String = ""
)


@Preview
@Composable
fun ProfileFormPreview() {
    LimpeanAppTheme {
        ProfileForm(
            state = ProfileFormState(),
            repeatedPassword = "",
            profilePhoto = {
                Text("Testando")
            },
            onEvent = {}
        )
    }
}