package br.senai.sp.jandira.limpeanapp.authentication.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel () : ViewModel() {

    var uiState by mutableStateOf(LoginState())
        private set

    private lateinit var userService: UserService

    private fun getData(loginForm: FormState<TextFieldState>): LoginApi {
        return loginForm.getData(LoginApi::class)
    }

    fun handle(loginForm: FormState<TextFieldState>) {
        uiState = uiState.copy(
            isLoading = true
        )
        if (loginForm.validate()) {
            val login = getData(loginForm)
            userService = RetrofitFactory.getUserService()
            userService.login(login = login).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    uiState = if(response.isSuccessful){
                        uiState.copy(
                            isLoading = false,
                            message = response.body().toString()
                        )
                    } else {
                        uiState.copy(
                            isLoading = false,
                            message = "Erro"
                        )
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    uiState = uiState.copy(
                        message = t.message
                    )
                }

            })
        }

    }
}