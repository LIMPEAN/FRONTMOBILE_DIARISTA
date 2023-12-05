package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist


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
data class DiaristProfile(
    val isLoading : Boolean = false,
    val diarist : Diarist = Diarist(),
    val error : String? = null
)