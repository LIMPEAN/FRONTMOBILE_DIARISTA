package br.senai.sp.jandira.limpeanapp.dados.modelos

import com.google.gson.annotations.SerializedName

data class AddressApi(
    val state: Number? = null,
    val city: String? = "",
    val cep: String? = "",
    @SerializedName("publicPlace")
    val publicPlace: String? = "",
    val complement: String? = null,
    val district: String? = "",
    @SerializedName("houseNumber")
    val houseNumber: String? = ""
)
