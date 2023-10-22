package br.senai.sp.jandira.limpeanapp.core.presentation.components.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import br.senai.sp.jandira.limpeanapp.core.presentation.components.PasswordField
import br.senai.sp.jandira.limpeanapp.core.presentation.components.text.NormalTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.TextFieldVariant

@Composable
fun MyTextField(
    state:  String,
    nameLabel : String,
    variant : TextFieldVariant = TextFieldVariant.Normal,
    onValueChange : (String) -> Unit
) {
    when(variant){
        is TextFieldVariant.Normal -> {
            NormalTextField(labelText = nameLabel, keyboardType = KeyboardType.Text, value = state, onValueChange = { onValueChange(it)})
        } TextFieldVariant.Date -> {

        } TextFieldVariant.Password -> {
            PasswordField(labelText = nameLabel, state = state, onValueChange = {onValueChange(it)} )
        }
    }
}





