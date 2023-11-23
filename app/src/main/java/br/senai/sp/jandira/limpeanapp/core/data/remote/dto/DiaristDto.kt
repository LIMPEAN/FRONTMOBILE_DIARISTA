package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class DiaristDto(
    @SerializedName("id_diarist")
    val id: Int,
    val statusAccount: List<StatusAccountDto>,
    val name: String,
    val cpf: String,
    val birthDate: LocalDate,
    val biography: String,
    val photoProfile: String,
    val email: String,
    @SerializedName("medium_value")
    val mediumValue: String,
    val gender: String,
    val assessment: List<AssentmentDto>,  // You can replace Any with a specific data class if needed
    val phone: List<PhoneDto>,
    val address: List<AddressDto>
)
fun DiaristDto.toDiarist() : Diarist {
    return Diarist(
        name = name,
        cpf = cpf,
        phones = phone.map { it.toPhone() },
        email = email,
        address = address.map { it.toAddress() },
        dateOfBirth = birthDate,
        photo = photoProfile,
        gender = getGenderByName(gender),
        assentments = assessment.map { it.toAssentment() },
        id = id
    )
}

fun getGenderByName(name: String): Gender {
    return Gender.values().find { it.name == name } ?: return Gender.OUTROS
}