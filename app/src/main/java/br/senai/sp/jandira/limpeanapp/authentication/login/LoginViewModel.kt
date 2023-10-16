package br.senai.sp.jandira.limpeanapp.authentication.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.authentication.AuthRepository
import br.senai.sp.jandira.limpeanapp.dados.api.RetrofitFactory
import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(LoginState())
        private set


    private fun getData(loginForm: FormState<TextFieldState>): LoginApi {
        return loginForm.getData(LoginApi::class)
    }

    fun handle(email: String, password:String) {


        val value = LoginApi(
            email = "caua@hotmail.com",
            password = "12345678"
        )
        val apiService = RetrofitFactory.getApiService()
        apiService.loginUser(value).enqueue( object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.i("SERVICE", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("SERVICE", t.message.toString())
            }

        })


    }



//            userService.login(login = login).enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    uiState = if(response.isSuccessful){
//                        uiState.copy(
//                            isLoading = false,
//                            message = response.body().toString()
//                        )
//                    } else {
//                        uiState.copy(
//                            isLoading = false,
//                            message = "Erro"
//                        )
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    uiState = uiState.copy(
//                        message = t.message
//                    )
//                }
//
//            })


}
