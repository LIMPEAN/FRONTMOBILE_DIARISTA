package br.senai.sp.jandira.limpeanapp.authentication.register.profile

import br.senai.sp.jandira.limpeanapp.regras.ValidateFormBuilder
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

object UserFormBuilder{
    fun build() : FormState<TextFieldState>{
        return FormState(listOf(
            addBiography(),
            addEmail(),
            addPassword(),
            addRepeatedPassword(),
            addProfilePhotoUri()
        ))
    }

    fun addBiography() : TextFieldState {
        return TextFieldState(
            name = "biography"
        )
    }
    fun addEmail() : TextFieldState {
        return TextFieldState(
            name = "email",
            validators = ValidateFormBuilder.validateEmail()
        )
    }
    fun addPassword() : TextFieldState {
        return TextFieldState(
            name = "password",
            validators = ValidateFormBuilder.validatePassword()
        )
    }
    fun addRepeatedPassword(): TextFieldState {
        return TextFieldState(
            name = "repeatedPassword",
            validators = listOf(
                ValidateFormBuilder.isRequired()
            )
        )
    }
    fun addProfilePhotoUri(): ChoiceState{
        return ChoiceState(
            name = "photoUri",
            validators = listOf(
                Validators.Required("É obrigatório escolher uma foto!")
            )
        )
    }

}

