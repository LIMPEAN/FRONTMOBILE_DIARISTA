package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form

import br.senai.sp.jandira.limpeanapp.core.presentation.components.textfield.TextFieldState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist

sealed class ProfileItem(val name: String, val variant : TextFieldVariant = TextFieldVariant.Normal) {
    object  Name : ProfileItem("Nome")
    object Cpf : ProfileItem( "Cpf")
    object Phone : ProfileItem( "Telefone ")
    object Email : ProfileItem( "Email")
    object Password : ProfileItem("Senha ")
    object RepeatedPassword : ProfileItem( "Senha Repetida")
    object DateOfBirth : ProfileItem(  "Nome")
}

fun rememberProfileForm(state: Diarist) : ProfileFormState{
    return ProfileFormState(
        name = state.name,
        cpf = state.cpf,
        phone = state.phone,
        email = state.email,
        password = state.password,
    )
}
