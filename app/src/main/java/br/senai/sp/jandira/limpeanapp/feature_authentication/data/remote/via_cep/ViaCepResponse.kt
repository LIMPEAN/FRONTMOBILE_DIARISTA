package br.senai.sp.jandira.limpeanapp.feature_authentication.data.remote.via_cep

data class ViaCepResponse(
    val cep: String = "",
    val logradouro: String = "",
    val complemento: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val uf: String = "",
    val ddd: String = "",
)
