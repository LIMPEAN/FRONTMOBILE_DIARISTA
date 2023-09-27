package br.senai.sp.jandira.limpeanapp.dados.repositorios


import br.senai.sp.jandira.limpeanapp.dados.api.ApiResponse
import br.senai.sp.jandira.limpeanapp.dados.api.servicos.UserService
import br.senai.sp.jandira.limpeanapp.dados.modelos.UserApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Status {
    const val LOADING = "loading"
    const val SUCCESS = "success"
    const val ERROR = "error"
}
class RepositorioDeUsuario(
    private val userService: UserService
) {

    interface RepositorioDeUsuarioCallback {
        fun onSuccess(message: String)
        fun onError(errorMessage: String)
    }

    fun adicionarUsuario(usuario: UserApi, callback: RepositorioDeUsuarioCallback) {
        // Define o status como "loading" antes de fazer a chamada da API
        callback.onSuccess(Status.LOADING)

        val call = userService.createUser(usuario)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Define o status como "success" em caso de sucesso
                    callback.onSuccess(Status.SUCCESS)
                } else {
                    // Define o status como "error" em caso de erro na resposta
                    callback.onSuccess(Status.ERROR)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Define o status como "error" em caso de falha na requisição
                callback.onSuccess(Status.ERROR)
            }
        })
    }

}