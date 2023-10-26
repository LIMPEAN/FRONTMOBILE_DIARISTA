package br.senai.sp.jandira.limpeanapp.home.data.remote

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GetDiaristResponse(
    val status: Int,
    val data: DiaristResponse
)


data class DiaristResponse(
    @SerializedName("id_diarist")
    val id: Int,
    val statusAccount: List<StatusAccountResponse>,
    val name: String,
    val cpf: String,
    val birthDate: Date,
    val biography: String,
    val photoProfile: String,
    val email: String,
    @SerializedName("medium_value")
    val mediumValue: String,
    val gender: String,
    val assessment: List<Any>,  // You can replace Any with a specific data class if needed
    val phone: List<PhoneResponse>,
    val address: List<AddressResponse>
)

data class StatusAccountResponse(
    val data: Date,
    val status: String
)

data class PhoneResponse(
    val ddd: String,
    @SerializedName("number_phone")
    val numberPhone: String
)

data class AddressResponse(
    val state: String,
    val city: String,
    val publicPlace: String,
    val district: String,
    val numberHouse: String,
    val cep: String,
    val complement: String
)
