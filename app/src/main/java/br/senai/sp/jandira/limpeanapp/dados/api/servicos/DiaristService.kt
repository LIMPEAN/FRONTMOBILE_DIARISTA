package br.senai.sp.jandira.limpeanapp.dados.api.servicos

import br.senai.sp.jandira.limpeanapp.dados.api.BaseResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.CriarDiarista
import retrofit2.http.POST

interface DiaristService {


    @POST("/cadastro")
    fun criarDiarista(diarista : CriarDiarista) : BaseResponse
}
