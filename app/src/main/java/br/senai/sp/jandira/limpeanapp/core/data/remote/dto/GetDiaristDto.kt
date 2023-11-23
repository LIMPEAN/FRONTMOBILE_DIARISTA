package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Phone
import com.google.gson.annotations.SerializedName
import java.util.Date

data class GetDiaristDto(
    val status: Int,
    val data: DiaristDto
)

data class StatusAccountDto(
    val data: Date,
    val status: String
)

data class PhoneDto(
    val ddd: String,
    @SerializedName("number_phone")
    val numberPhone: String
)
fun PhoneDto.toPhone() : Phone {
    return Phone(
        ddd = ddd,
        number = numberPhone
    )
}

data class AddressDto(
    val state: String,
    val city: String,
    val publicPlace: String,
    val district: String,
    val houseNumber: String,
    val cep: String,
    val complement: String
)
fun AddressDto.toAddress() : Address {
    return Address(
        cep = cep,
        city = city,
        street = publicPlace,
        district = district,
        state = state,
        number = houseNumber,
        complement = complement
    )
}