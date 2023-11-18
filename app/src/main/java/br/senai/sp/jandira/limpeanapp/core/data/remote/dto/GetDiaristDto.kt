package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

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

data class AddressDto(
    val state: String,
    val city: String,
    val publicPlace: String,
    val district: String,
    val houseNumber: String,
    val cep: String,
    val complement: String
)
