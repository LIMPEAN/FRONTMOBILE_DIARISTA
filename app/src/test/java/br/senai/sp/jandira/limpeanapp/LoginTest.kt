package br.senai.sp.jandira.limpeanapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginFormBuilder
import br.senai.sp.jandira.limpeanapp.authentication.login.LoginViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import org.junit.Test

class LoginTest {



    @Composable
    @Test
    fun login_diarist_when_data_correctly(){
        val loginViewModel = viewModel<LoginViewModel>()
        val loginForm = LoginFormBuilder.build()
        val email: TextFieldState = loginForm.getState("email")
        val passworrd : TextFieldState = loginForm.getState("password")
        email.change("caua@hotmail.com")
        passworrd.change("12345678")


    }
}