package br.senai.sp.jandira.limpeanapp.registration

data class RegistrationState(
<<<<<<< HEAD
    val typeUser: String = "diarist",
    val email: String = "user@gmail.com",
    val password: String = "1234567@",
    val nameUser: String = "Jessica Jones",
    val photoUser: String = "LocalphotoURI",
    val phone: String = "9130153151",
    val ddd: String = "11",
    val birthDate: String = "2022-23-2022",
    val idGender: Int = 1,
    val cpf: String = "456-456-45-87",
    val biography: String? = null,
    val address: Address? = null
=======
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
>>>>>>> 5556fbaec5c2f9b4ada431c6d980d34db3de1283
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