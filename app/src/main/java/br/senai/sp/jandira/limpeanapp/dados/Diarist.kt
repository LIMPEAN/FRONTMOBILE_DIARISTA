package br.senai.sp.jandira.limpeanapp.dados

import br.senai.sp.jandira.limpeanapp.dados.User

data class Diarista(
    val id: Long?,
    val dadosDeUsuario : Usuario,
    val media : Number
)

//data class Cliente(
//    val id: Long?,
//    val dadosDeUsuario : Usuario,
//    val casas : List<Casa>?
//)
//data class Casa (
//    val id: Long?,
//    val enderecoLocal : EnderecoLocal,
//    val comodos : List<Comodo>
//)
//data class Comodo (
//    val id: Long?,
//    nome,
//
//)