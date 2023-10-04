package br.senai.sp.jandira.limpeanapp.authentication.register.profile

import android.net.Uri
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState

data class UserState(
    val photoUri: String,
    val biography : String,
    val email : String,
    val password : String,
    val repeatedPassword: String
)



