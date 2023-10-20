package br.senai.sp.jandira.limpeanapp.core.presentation.components.textfield


import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form.TextFieldVariant


data class TextFieldState(
    val state: String,
    val nameLabel : String,
    val variant : TextFieldVariant,
)
