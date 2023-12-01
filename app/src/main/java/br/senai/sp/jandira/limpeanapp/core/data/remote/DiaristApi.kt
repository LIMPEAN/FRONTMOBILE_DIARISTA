package br.senai.sp.jandira.limpeanapp.core.data.remote


import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseToken


import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.DiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.GetDiaristDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.InvitesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.ServiceDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.StatusTokenDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.UpdatePriceDTO
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.assentment.CreateAssentmentDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.get_diarist.GetDiaristDTOX
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduleClient
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduledCleaningDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.UpdateStatusDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface DiaristApi {

    @GET("diarist")
    suspend fun getDiarist() : GetDiaristDTOX

    @PUT("diarist")
    suspend fun updateDiarist(diaristDto: DiaristDto)



    //Pega todos os servicos em aberto do cliente
    //TELA DE ENCONTRAR SERVICOS
    @GET("client/service-open")
    suspend fun getOpenServices() : OpenServicesDto


    //Atualizar os status do serviço do cliente
    // para recusar, mudar para rejeitado,
    //PARA FINALIZAR : mudar para finalizar
    @PUT("diarist/schedule-service")
    suspend fun putStatusService(
        @Query("idService") idService : Number,
        @Query("idStatus") idStatus: Number? = StatusService.AGENDADO.codigo
    ) : UpdateStatusDto


    //Pega todos servicos do diarista, como convite. Ou pode filtrar pelo status: Em andamento, Finalizado ...
    @GET("diarist/service")
    suspend fun getServices(@Query("id") idStatus : Number) : BaseDto<List<ScheduleClient>>


    //Mesmo que pegar os serviços, mas pegando um pelo id
    @GET("diarist/service")
    suspend fun getInviteById(@Query("id") id : Number)


    @GET("diarist/service/token")
    suspend fun getToken(@Query("idService") idService : Number) : BaseResponseToken

  //Atualiza o preço do serviço

    @PUT("diarist/service/price")
    suspend fun updatePrice(@Body updatePriceInfo : UpdatePriceDTO) : BaseResponseDto


    //Pega o codigo do servico do cliente (para iniciar o serviço)
    @GET("diarist/service/token")
    suspend fun getTokenFromService(@Query("idService") idService: Number): StatusTokenDto




    @POST("assessment")
    suspend fun sendAssentment(
        @Body assentmentDto: CreateAssentmentDto
    ) : BaseResponseDto


    @DELETE("diarist")
    suspend fun deleteDiarist() : BaseResponseDto

}
