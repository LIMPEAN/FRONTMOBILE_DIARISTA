package br.senai.sp.jandira.limpeanapp.dados.modelos

import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("state")
    val numeroDoEstado : Number? = null,
    @SerializedName("city")
    val cidade : String? = "",
    val cep : String? = "",
    @SerializedName("publicPlace")
    val rua : String? = "",
    @SerializedName("complement")
    val complemento : String? = "",
    @SerializedName("district")
    val bairro : String? = "",
    @SerializedName("houseNumber")
    val numeroDaCasa : Number? = null
)