package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register

import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormState

data class RegisterState(
    val diarist : Diarist,
    val address : Address,
    val profileForm : ProfileFormState,
    val addressForm: AddressFormState? = null,
    val isLoading : Boolean = false
)
