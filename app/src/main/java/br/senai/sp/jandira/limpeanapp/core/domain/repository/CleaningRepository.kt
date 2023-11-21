package br.senai.sp.jandira.limpeanapp.core.domain.repository

import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceToken
import br.senai.sp.jandira.limpeanapp.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface CleaningRepository {


    suspend fun getOpenServices() : OpenServicesDto

    suspend fun acceptService(id: Number)

    fun getScheduledCleanings() : Flow<List<Cleaning>>


    suspend fun startService(id: Number, dateTime : LocalDateTime = LocalDateTime.now()) : ServiceToken

    suspend fun endService(id: Number)
    suspend fun sendProposal(id: Number,price : Double)


    fun getFinishedServices() : Flow<List<Cleaning>>
    fun getInvites() : Flow<List<Cleaning>>
    suspend fun getCleaningDetail(id: Number) : Cleaning?

}
