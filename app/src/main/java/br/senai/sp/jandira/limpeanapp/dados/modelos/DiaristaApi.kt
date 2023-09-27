package br.senai.sp.jandira.limpeanapp.dados.modelos

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.Date

data class DiaristaApi(
    @SerializedName("typeUser")
    val nomeTipoUsuario: String? = "",
    val email: String? = "",
    @SerializedName("password")
    val senha: String? = "",
    @SerializedName("nameUser")
    val nomeDaPessoa: String? = "",
    @SerializedName("photoUser")
    val fotoUri: String? = "",
    @SerializedName("phone")
    val telefone: String? = "",
    val ddd: Int? = null,
    @SerializedName("birthDate")
    val dataDeNascimento: String? = null,
    @SerializedName("idGender")
    val idDoGenero: Number? = 1,
    val cpf: String? = "",
    @SerializedName("biography")
    val biografia: String? = null,
    @SerializedName("averagePrice")
    val precoMedio: Double? = null,
    @SerializedName("address")
    val enderecoLocal: Endereco? = null
)