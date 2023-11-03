package br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.errors

sealed class InvalidDiarist(message : String) : Exception(message) {
    object InvalidEmail : InvalidDiarist("O tipo do email é inválido. [exemplo@algo.com]")
    object InvalidDate : InvalidDiarist("O formato da data é inválido![2000/10/2023]")
    object UserAlreadyExists : InvalidDiarist("Este usuário já está cadastrado! [Email e Telefone]")
    class DataEmpty(name: String) : InvalidDiarist("Este campo não pode ser estar vazio! $name")

    data class RepositoryError(override val message: String): InvalidDiarist(message)
}
