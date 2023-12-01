package br.senai.sp.jandira.limpeanapp.core.domain.repository


import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseToken
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduleClient
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduledCleaningDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.UpdateStatusDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceToken
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface CleaningRepository {


    suspend fun getOpenServices() : OpenServicesDto

    suspend fun acceptService(id: Number) : UpdateStatusDto

    suspend fun getScheduledCleanings() : BaseDto<List<ScheduleClient>>


    suspend fun startService(id: Number) : BaseResponseToken

    suspend fun endService(id: Number) : UpdateStatusDto
    suspend fun sendProposal(id: Number,price : Double) : BaseResponseDto

    suspend fun getStartedService() : Cleaning?
    suspend fun getFinishedServices() : List<Cleaning>
    suspend fun getInvites() : List<Cleaning>
    suspend fun getCleaningDetail(id: Number) : Cleaning?

}
