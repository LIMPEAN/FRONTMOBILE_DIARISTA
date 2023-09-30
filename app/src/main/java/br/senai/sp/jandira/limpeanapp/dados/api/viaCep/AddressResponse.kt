package br.senai.sp.jandira.limpeanapp.dados.api.viaCep

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val ibge: String?,
    val gia: String?,
    val ddd: String?,
    val siafi: String?,
)
