package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Avaliacao(
    val id: Int,
    val name: String,
    val photo: String,
    val stars: Int,
    val dataHour: String
)


data class DiaristDto(
    @SerializedName("id_diarist")
    val id: Int,
    val statusAccount: List<StatusAccountDto>,
    val name: String,
    val cpf: String,
    val birthDate: Date,
    val biography: String,
    val photoProfile: String,
    val email: String,
    @SerializedName("medium_value")
    val mediumValue: String,
    val gender: String,
    val assessment: List<Avaliacao>,
    val phone: List<PhoneDto>,
    val address: List<AddressDto>
)
