package br.senai.sp.jandira.limpeanapp.telas.cadastro

import android.net.Uri
import br.senai.sp.jandira.limpeanapp.dados.EnderecoLocal
import br.senai.sp.jandira.limpeanapp.dados.UserTypesRepository
import br.senai.sp.jandira.limpeanapp.regras.TipoDeUsuario

data class CadastroDeUsuario(
    val tipoDeUsuario: String? = "diarist",
    val email: String? = "user@gmail.com",
    val password: String? = "1234567@",
    val nameUser: String? = "Jessica Jones",
    val photoUser: String? = "https://qualquercoisa",
    val phone: Number? = 9130153151,
    val ddd: Number? = 11,
    val birthDate: String? = "2022-23-2022",
    val idGender: Int? = 1,
    val cpf: String? = "456-456-45-87",
    val biography: String? = null,
    val address: EnderecoLocal? = null,
    val average : Number? = null
)
data class CadastroDeDiarista(
    val usuario: CadastroDeUsuario,
    val average: Number
)