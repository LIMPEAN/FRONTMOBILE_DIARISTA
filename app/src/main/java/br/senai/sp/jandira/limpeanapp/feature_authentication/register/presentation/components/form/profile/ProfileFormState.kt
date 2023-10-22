package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile

import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist


data class ProfileFormState(
    val name : String = "",
    val cpf : String = "",
    val phone: String = "",
    val ddd: String = "",
    val email : String = "",
    val password: String = "",
)

fun createProfileFormState(diarist : Diarist) : ProfileFormState {
    return ProfileFormState(
        name = diarist.name,
        password = diarist.password,
        cpf = diarist.cpf,
        ddd = diarist.ddd,
        phone = diarist.phone,
        email = diarist.email
    )
}
