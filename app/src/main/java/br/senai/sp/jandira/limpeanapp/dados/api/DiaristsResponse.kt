package br.senai.sp.jandira.limpeanapp.dados.api

import com.google.gson.annotations.SerializedName


data class DiaristaResponse(
    @SerializedName("status") val status: Number,
    @SerializedName("diarists")val diarists: List<DiaristsModel>
)

data class DiaristsModel(
    @SerializedName("id_diarista") val idDiarista: Int,
    @SerializedName("nome_diarista") val nomeDiarista: String,
    @SerializedName("cpf_diarista") val cpfDiarista: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("biografia") val biografia: String?,
    @SerializedName("foto_perfil") val fotoPerfil: String,
    @SerializedName("email_diarista") val emailDiarista: String,
    @SerializedName("senha_diarista") val senhaDiarista: String,
    @SerializedName("media_valor") val mediaValor: String,
    @SerializedName("genero") val genero: String,
    @SerializedName("ddd") val ddd: String,
    @SerializedName("numero_telefone") val numeroTelefone: String,
    @SerializedName("endereco_logradouro") val enderecoLogradouro: String,
    @SerializedName("endereco_bairro") val enderecoBairro: String,
    @SerializedName("endereco_cep") val enderecoCep: String,
    @SerializedName("endereco_numero_residencia") val enderecoNumeroResidencia: String,
    @SerializedName("endereco_complemento") val enderecoComplemento: String?,
    @SerializedName("cidade") val cidade: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("estrelas") val estrelas: Int?,
    @SerializedName("comentario") val comentario: String?,
    @SerializedName("status_conta") val statusConta: Boolean,
    @SerializedName("data_status_diarista") val dataStatusDiarista: String
)
