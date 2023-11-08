package br.senai.sp.jandira.limpeanapp.ui.features.profile


data class ProfileState(
    val foto_perfil : String,
    val nome_diarista : String,
    val avaliacaoMedia : Double,
    val biografia : String,
    val dadosPessoais : DadosPessoais,
)
data class DadosPessoais(
    val genero : String,
    val cidade : String,
    val estado : String,
    val numero_telefone: String
)