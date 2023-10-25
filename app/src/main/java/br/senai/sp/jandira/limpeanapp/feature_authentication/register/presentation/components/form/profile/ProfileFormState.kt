package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile


data class ProfileFormState(
    val name : String = "",
    val cpf : String = "",
    val phone: String = "",
    val ddd: String = "",
    val email : String = "",
    val password: String = "",
) {

}


