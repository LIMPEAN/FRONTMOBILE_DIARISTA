package br.senai.sp.jandira.limpeanapp.regras

data class TipoDeUsuario (
    var id : Long,
    var nomeDaApi : String,
    var nomeEmPortugues: String
) {

    companion object {
        fun pegaUmExemplo(id : Number) : TipoDeUsuario{
           return if(id == 0){
                TipoDeUsuario(0, "diarist", "Diarista")
            } else {
                TipoDeUsuario(1, "cliente", "Cliente")
            }
        }
    }

}