package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormState

data class RegisterState(
    val diarist : Diarist,
    val address : Address,
    val profileForm : ProfileFormState,
    val addressForm: AddressFormState? = null,
    val isLoading : Boolean = false
)
