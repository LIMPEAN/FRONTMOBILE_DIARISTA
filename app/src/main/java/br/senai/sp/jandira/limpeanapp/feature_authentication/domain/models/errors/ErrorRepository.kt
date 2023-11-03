package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.errors

sealed class ErrorRepository(message: String) : Exception(message){
    data class ClientError(override val message: String) : ErrorRepository(message = message)
    data class ServerError(override val message: String) : ErrorRepository(message = message)
}
