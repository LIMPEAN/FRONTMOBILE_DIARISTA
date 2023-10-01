package br.senai.sp.jandira.limpeanapp.dados.viaCep

import com.google.gson.annotations.SerializedName

data class BaseResponseCep(
    @SerializedName("cep") var cep: String? = "",
    @SerializedName("logradouro") var logradouro: String? = "",
    @SerializedName("complemento") var complemento: String? = "",
    @SerializedName("bairro") var bairro: String? = "",
    @SerializedName("localidade") var localidade: String? = "",
    @SerializedName("uf") var uf: String? = "",
    @SerializedName("ddd") var ddd: String? = "",
)
