package br.senai.sp.jandira.limpeanapp.dados

import android.net.Uri
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.Phone
import br.senai.sp.jandira.limpeanapp.regras.UserType

data class Usuario(
    val tipoUsuario : UserType? = null,
    val dadosDePessoa : Pessoa? = null,
    var fotoPerfil : Uri? = null,
    var email: String? = null,
    var senha: String? = null,
    val telefone: Phone? = null,
    var biografia: String? = null,
    val endereco: EnderecoLocal? = null
)


