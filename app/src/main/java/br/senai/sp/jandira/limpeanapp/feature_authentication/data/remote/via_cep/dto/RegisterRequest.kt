package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep.dto

data class RegisterRequest(
    val typeUser: String,
    val email: String,
    val password: String,
    val nameUser: String,
    val photoUser: String,
    val phone: String,
    val ddd: String,
    val birthDate: String,
    val idGender: Int,
    val cpf: String,
    val biography: String?,
    val averagePrice: String,
    val address: AddressRequest
)
