package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist

import br.senai.sp.jandira.limpeanapp.core.data.mapper.parseStringToDateTime
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.AssentmentDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.getGenderByName
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class DiaristDto(
    val address: List<Addres>,
    val assessment: List<AssentmentDto>,
    val biography: String,
    val birthDate: String,
    val cpf: String,
    val email: String,
    val gender: String,
    @SerializedName("id_diarist") val idDiarist: Int,
    @SerializedName("medium_value") val mediumValue: String,
    val name: String,
    val phone: List<Phone>,
    val photoProfile: String,
    val statusAccount: List<StatusAccount>
)
fun DiaristDto.toDiarist() : Diarist {
    return Diarist(
        name = name,
        cpf = cpf,
        address = address.map { it.toAddress() },
        biography = biography,
        phones = phone.map { it.toPhone() },
        email = email,
        dateOfBirth = parseDateOfBirth(birthDate),
        photo = photoProfile,
        gender = getGenderByName(gender),
        assentments = assessment.map { it.toAssentment() },
        id = idDiarist
    )
}
fun parseDateOfBirth(date : String) : LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
    return LocalDate.parse(date, formatter)
}
fun Addres.toAddress() : Address {
    return Address(
        cep = cep,
        city = city,
        state = state,
        district = district,
        complement = complement,
        number = numberHouse,
        publicPlace = publicPlace
    )
}

fun Phone.toPhone() : br.senai.sp.jandira.limpeanapp.core.domain.models.Phone {
    return br.senai.sp.jandira.limpeanapp.core.domain.models.Phone(
        number = number_phone,
        ddd = ddd
    )
}

fun AssentmentDto.toAssentment() : Assentment {
    return Assentment(
        comment = comment,
        date = LocalDate.parse(date),
        hour = LocalTime.parse(hour),
        personEvaluatedId = personEvaluatedId,
        star = star
    )
}