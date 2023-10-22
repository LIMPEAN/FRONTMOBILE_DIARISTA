package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormState

data class RegisterState(
    val title: String,
    val description : String,
    val profileForm : ProfileFormState,
    val addressForm: AddressFormState? = null,
    val titleButton : String,
    val onButtonClick : (String) -> Unit
)
@Composable
fun rememberRegisterState(): RegisterState{
    return RegisterState(
        title = stringResource(R.string.register_title),
        description = stringResource(R.string.register_description),
        profileForm = ProfileFormState(),
        titleButton = "Teste",
        onButtonClick = {}
    )
}