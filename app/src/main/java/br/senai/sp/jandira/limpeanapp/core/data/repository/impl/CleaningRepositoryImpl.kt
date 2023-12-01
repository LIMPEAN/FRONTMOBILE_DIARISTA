package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.BaseResponseToken
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.OpenServicesDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.UpdatePriceDTO
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.ScheduleClient
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.UpdateStatusDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning.toCleaning
import br.senai.sp.jandira.limpeanapp.core.data.repository.fakeCleanings
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import br.senai.sp.jandira.limpeanapp.core.domain.repository.CleaningRepository
import br.senai.sp.jandira.limpeanapp.presentation.features.invites.formatDoubleToString
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

    override suspend fun endService(id: Number): UpdateStatusDto {
        return try {
            api.putStatusService(
                idService = id,
                idStatus = StatusService.FINALIZADO.codigo
            )
        } catch (e : Exception){
            throw e
        }
    }

    override suspend fun sendProposal(id: Number, price: Double) : BaseResponseDto{
       try {
            return api.updatePrice(
                updatePriceInfo = UpdatePriceDTO(
                    idService = id,
                    newValue = formatDoubleToString(price)
                )
            )

        } catch (e: Exception){
            throw e
        }

    }


    override suspend fun getCleaningDetail(id: Number): Cleaning? {
        return Cleaning(1)
    }

    override suspend fun getStartedService(): Cleaning? {
        val cleanings = api.getServices(StatusService.EM_ANDAMENTO.codigo).data.map { it.client.toCleaning() }
        return cleanings.minByOrNull { it.dateTime }
    }

    override suspend fun getFinishedServices(): List<Cleaning> {
        return api.getServices(
            idStatus = StatusService.FINALIZADO.codigo
        ).data.map { it.client.toCleaning() }
    }

    override suspend fun getInvites(): List<Cleaning> {
        return api.getServices(
            idStatus = StatusService.EM_ABERTO.codigo
        ).data.map { it.client.toCleaning() }
    }
}