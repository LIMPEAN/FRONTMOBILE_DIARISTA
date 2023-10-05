package br.senai.sp.jandira.limpeanapp.authentication.login

import br.senai.sp.jandira.limpeanapp.regras.ValidateFormBuilder
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState

data class LoginState(
    val email : TextFieldState = LoginFormBuilder.addEmail(),
    val password : TextFieldState = LoginFormBuilder.addPassword(),
    val isLoading : Boolean = false,
    val message : String? = null
)
data class LoginApi(
    val typeUser: String = "diarist",
    val email : String,
    val password: String
)



object LoginFormBuilder {
    fun build(): FormState<TextFieldState>{
        return FormState(
            listOf(
                addEmail(),
                addPassword()
            )
        )
    }
    fun addEmail(): TextFieldState{
        return TextFieldState(
            name = "email",
            validators = ValidateFormBuilder.validateEmail()
        )
    }
    fun addPassword(): TextFieldState{
        return TextFieldState(
            name = "password",
            validators = ValidateFormBuilder.validatePassword()
        )
    }
}
