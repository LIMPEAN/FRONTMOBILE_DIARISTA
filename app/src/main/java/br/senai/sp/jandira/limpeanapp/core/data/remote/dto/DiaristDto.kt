package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

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
    val assessment: List<Any>,  // You can replace Any with a specific data class if needed
    val phone: List<PhoneDto>,
    val address: List<AddressDto>
)
