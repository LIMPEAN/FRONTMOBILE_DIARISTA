package br.senai.sp.jandira.limpeanapp.dados.modelos

import com.google.gson.annotations.SerializedName

data class CriarDiarista(
    @SerializedName("typeUser")
    val nomeTipoUsuario : String,
    val email : String,
    @SerializedName("password")
    val senha : String,
    @SerializedName("nameUser")
    val nomeDaPessoa : String,
    @SerializedName("photoUser")
    val fotoUri : String,
    @SerializedName("phone")
    val telefone : String,
    val ddd : String,
    @SerializedName("birthDate")
    val dataDeNascimento : String,
    @SerializedName("idGender")
    val idDoGenero : Number,
    val cpf : String,
    @SerializedName("biography")
    val biografia : String? = null,
    @SerializedName("averagePrice")
    val precoMedio : Number? = null,
    @SerializedName("address")
    val enderecoLocal : Endereco
)