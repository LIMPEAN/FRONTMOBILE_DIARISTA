package br.senai.sp.jandira.limpeanapp.registration

data class RegistrationState(
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
    val address: Address
)

data class Address(
    val typeHouse: Int,
    val state: Int,
    val city: String,
    val cep: String,
    val publicPlace: String,
    val complement: String?,
    val district: String,
    val houseNumber: String
)