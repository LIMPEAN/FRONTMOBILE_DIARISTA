package br.senai.sp.jandira.limpeanapp.dados.modelos

import android.net.Uri
import br.senai.sp.jandira.limpeanapp.regras.Pessoa
import br.senai.sp.jandira.limpeanapp.regras.Phone
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario

data class Usuario(
    val tipoUsuario : TipoDeUsuario? = null,
    val dadosDePessoa : Pessoa? = null,
    var fotoPerfil : Uri? = null,
    var email: String? = null,
    var senha: String? = null,
    val telefone: Phone? = null,
    var biografia: String? = null,
    val endereco: Endereco? = null
)


