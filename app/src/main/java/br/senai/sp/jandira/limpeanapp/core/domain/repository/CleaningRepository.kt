package br.senai.sp.jandira.limpeanapp.core.domain.repository


import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
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


    suspend fun startService(id: Number, dateTime : LocalDateTime = LocalDateTime.now()) : ServiceToken

    suspend fun endService(id: Number)
    suspend fun sendProposal(id: Number,price : Double)


    fun getFinishedServices() : Flow<List<Cleaning>>
    fun getInvites() : Flow<List<Cleaning>>
    suspend fun getCleaningDetail(id: Number) : Cleaning?

}
