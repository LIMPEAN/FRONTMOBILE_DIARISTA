package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormState

data class RegisterState(
    val diarist : Diarist,
    val address : Address,
    val profileForm : ProfileFormState,
    val addressForm: AddressFormState? = null,
    val isLoading : Boolean = false
)
