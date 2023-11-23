package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseToken
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.UpdatePriceDTO
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduleClient
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduledCleaningDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.UpdateStatusDto
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceToken
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject


class CleaningRepositoryImpl @Inject constructor(
    private val api : DiaristApi,
) : CleaningRepository{
    override suspend fun getOpenServices(): OpenServicesDto {
        return api.getOpenServices()
    }

    override suspend fun acceptService(id: Number) : UpdateStatusDto {
        try {
            return api.putStatusService(
                idService = id,
                idStatus = StatusService.AGENDADO.codigo
            )
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getScheduledCleanings(): BaseDto<List<ScheduleClient>> {
        return api.getServices(idStatus = StatusService.AGENDADO.codigo)
    }

    override suspend fun startService(idService: Number): BaseResponseToken {
        try {
            return api.getToken(idService)
        } catch (e : Exception){
            throw e
        }
    }

    override suspend fun endService(id: Number) {
        try {
            api.putStatusService(
                idService = id,
                idStatus = StatusService.FINALIZADO.codigo
            )
        } catch (e : Exception){
            throw e
        }
    }

    override suspend fun sendProposal(id: Number, price: Double) {
        try {
            api.updatePrice(
                updatePriceInfo = UpdatePriceDTO(
                    idService = id,
                    newValue = price.toString()
                )
            )

        } catch (e: Exception){
            throw e
        }

    }

    override fun getFinishedServices(): Flow<List<Cleaning>> {
        return flow {
            while (true){
                emit(fakeCleanings)
                delay(5000)
            }
        }
    }

    override fun getInvites(): Flow<List<Cleaning>> {
        return flow {
            emit(fakeCleanings)
            delay(5000)
        }
    }

    override suspend fun getCleaningDetail(id: Number): Cleaning? {
        return Cleaning(1)
    }
}