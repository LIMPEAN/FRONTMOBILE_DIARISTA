package br.senai.sp.jandira.limpeanapp.core.data.repository.impl

import android.util.Log
import br.senai.sp.jandira.limpeanapp.core.data.mapper.toCleaning
import br.senai.sp.jandira.limpeanapp.core.data.remote.DiaristApi
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.UpdatePriceDTO
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
    private val api : DiaristApi
) : CleaningRepository{
    override fun getOpenServices(): Flow<List<Cleaning>> {
        return flow {
            while (true){
                val servicesDto = api.getOpenServices()
                val services = servicesDto.data.map {
                    it.service.toCleaning()
                }
                Log.i("SERVICES", services.toString())
                emit(services)
                delay(5000)
            }
        }
    }

    override suspend fun acceptService(id: Number) {
        try {
            api.putStatusService(
                idService = id,
                idStatus = StatusService.AGENDADO.codigo
            )
        } catch (e: Exception){
            throw e
        }
    }

    override fun getScheduledCleanings(): Flow<List<Cleaning>> {
        return flow {
            while (true){
                val servicesDto = api.getOpenServices()
                Log.i("SERVICES", servicesDto.toString())
                val services = servicesDto.data.map { it.service.toCleaning() }
                emit(services)
                delay(5000)
            }
        }
    }

    override suspend fun startService(id: Number, dateTime: LocalDateTime): ServiceToken {
        try {
            val result = api.getTokenFromService(idService = id)
            return ServiceToken(value = result.token)
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