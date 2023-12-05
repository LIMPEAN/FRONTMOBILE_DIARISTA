package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile

import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist


data class ProfileFormState(
    val name : String = "",
    val cpf : String = "",
    val phone: String = "",
    val ddd: String = "",
    val email : String = "",
    val password: String = "",
) {

}




