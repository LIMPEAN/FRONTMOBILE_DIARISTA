package br.senai.sp.jandira.limpeanapp.core.domain.usecases.update_diarist

data class UpdateDiaristDto(
    val address: Address,
    val averagePrice: String? = null,
    val biography: String?  = null,
    val idGender: Number?  = null,
    val name: String?  = null,
    val password: String? = null,
    val phones: List<Phone>? = null,
    val photoUser: String? = null
)