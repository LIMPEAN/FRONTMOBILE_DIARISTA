package br.senai.sp.jandira.limpeanapp.core.presentation.components.textfield


import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.TextFieldVariant


data class TextFieldState(
    val state: String,
    val nameLabel : String,
    val variant : TextFieldVariant,
)
