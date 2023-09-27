package br.senai.sp.jandira.limpeanapp.dados.api.servicos

import br.senai.sp.jandira.limpeanapp.dados.api.BaseResponse
import br.senai.sp.jandira.limpeanapp.dados.modelos.DiaristaApi
import retrofit2.http.POST

interface DiaristService {


    @POST("/cadastro")
    fun criarDiarista(diarista : DiaristaApi) : BaseResponse
}
