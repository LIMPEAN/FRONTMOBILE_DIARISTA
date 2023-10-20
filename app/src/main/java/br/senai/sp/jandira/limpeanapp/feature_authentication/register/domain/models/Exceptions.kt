package br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models


sealed class InvalidDiaristException(message : String) : Exception(message) {
    object InvalidEmail : InvalidDiaristException("O tipo do email é inválido. [exemplo@algo.com]")
    object InvalidDate : InvalidDiaristException("O formato da data é inválido![2000/10/2023]")
    object UserAlreadyExists : InvalidDiaristException("Este usuário já está cadastrado! [Email e Telefone]")
    class DataEmpty(name: String) : InvalidDiaristException("Este campo não pode ser estar vazio! $name")
}
