package br.senai.sp.jandira.limpeanapp.dados

import br.senai.sp.jandira.limpeanapp.regras.Genero


//API DO VIA CEP
data class EnderecoViaCep(
    val cep: String? = "06655/450",
    val logradouro : String? = "Rua da giovana",
    val bairro: String? = "Jardim Briquet" ,
    val cidade: String? = "Itapevi",
    val estado: String? = "São Paulo"
)
data class EnderecoLocal(
    val enderecoViaCep : EnderecoViaCep? = null,
    val tipoResidencia : TipoResidencia? = null ,
    val complemento : String? = null,
    val numero : Int? = 0
)
data class TipoResidencia(
    val id: Int? = 0,
    val nome: String = "Apartamento"
){
    companion object {
        fun values(): List<TipoResidencia> {
            return listOf(
                TipoResidencia(1, "Casa"),
                TipoResidencia(2, "Apartamento"),
                TipoResidencia(3, "Sobrado"),
                TipoResidencia(4, "Condomínio"),
                TipoResidencia(5, "Chácara"),
                TipoResidencia(6, "KitNet")
            )
        }
    }
}