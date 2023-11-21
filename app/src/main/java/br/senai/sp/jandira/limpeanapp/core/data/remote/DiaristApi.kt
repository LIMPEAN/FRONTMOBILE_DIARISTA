package br.senai.sp.jandira.limpeanapp.core.data.remote

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.ServiceDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.UpdatePriceDTO
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import okhttp3.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface DiaristApi {

    @GET("diarist")
    suspend fun getDiarist() : GetDiaristDto

    @PUT("diarist")
    suspend fun updateDiarist(diaristDto: DiaristDto)


    //Pega todos os servicos em aberto do cliente
    //TELA DE ENCONTRAR SERVICOS
    @GET("client/service-open")
    suspend fun getOpenServices() : OpenServicesDto


    //Atualizar os status do serviço do cliente
    // para recusar, mudar para rejeitado,
    //PARA FINALIZAR : mudar para finalizar
    @PUT("/diarist/schedule-service")
    suspend fun putStatusService(
        @Query("idService") idService : Number,
        @Query("idStatus") idStatus: Number? = StatusService.AGENDADO.codigo
    )


    //Pega todos servicos do diarista, como convite. Ou pode filtrar pelo status: Em andamento, Finalizado ...
    @GET("/diarist/service")
    suspend fun getInvites()

    //Mesmo que pegar os serviços, mas pegando um pelo id
    @GET("diarist/service")
    suspend fun getInviteById(@Query("id") id : Number)

    //Atualiza o preço do serviço
    @PUT("diarist/service/price")
    suspend fun updatePrice(updatePriceInfo : UpdatePriceDTO) : BaseDto


    //Pega o codigo do servico do cliente (para iniciar o serviço)
    @GET("diarist/service/token")
    suspend fun getTokenFromService(@Query("idService") idService: Number)

//    @POST("assessment")
//    suspend fun

    @DELETE("diarist")
    suspend fun deleteDiarist()
}
