package br.senai.sp.jandira.limpeanapp.dados.modelos

import com.google.gson.annotations.SerializedName

data class UserApi(
    @SerializedName("typeUser")
    val userType: String? = "",
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("nameUser")
    val userName: String? = "",
    @SerializedName("photoUser")
    val photoUrl: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    val ddd: String? = null,
    @SerializedName("birthDate")
    val birthDate: String? = null,
    @SerializedName("idGender")
    val genderId: Number? = 1,
    val cpf: String? = "",
    @SerializedName("biography")
    val biography: String? = null,
    @SerializedName("averagePrice")
    val averagePrice: Double? = null,
    @SerializedName("address")
    val address: AddressApi? = null
)
