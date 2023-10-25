package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models


sealed class InvalidDiaristException(message : String) : Exception(message) {
    object InvalidEmail : InvalidDiaristException("O tipo do email é inválido. [exemplo@algo.com]")
    object InvalidDate : InvalidDiaristException("O formato da data é inválido![2000/10/2023]")
    object UserAlreadyExists : InvalidDiaristException("Este usuário já está cadastrado! [Email e Telefone]")
    class DataEmpty(name: String) : InvalidDiaristException("Este campo não pode ser estar vazio! $name")

    data class RepositoryError(override val message: String): InvalidDiaristException(message)
}


sealed class ErrorRepositoryException(message: String) : Exception(message){
    data class ClientError(override val message: String) : ErrorRepositoryException(message = message)
    data class ServerError(override val message: String) : ErrorRepositoryException(message = message)
}


