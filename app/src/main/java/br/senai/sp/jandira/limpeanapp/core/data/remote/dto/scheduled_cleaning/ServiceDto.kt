package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

import br.senai.sp.jandira.limpeanapp.core.data.mapper.parseStringToDateTime
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.models.Client
import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceStatus
import com.google.gson.annotations.SerializedName

data class ServiceDto(
    val address: Address,
    val biography: String?,
    val clientId: Int,
    @SerializedName("date_hour")val dateHour: String,
    val name: String,
    @SerializedName("obeservation")val observation: String,
    val photo: String,
    @SerializedName("question")val questions: List<Question>,
    @SerializedName("room")val rooms: List<Room>,
    val serviceId: Int,
    @SerializedName("status_service")val statusService: List<StatusService>,
    val tasks: String,
    @SerializedName("type_clean")val typeCleaning: String,
    val value: String?
)
fun ServiceDto.toCleaning() : Cleaning {

    val value = value ?: "0,0"
    return Cleaning(
        id = serviceId,
        price = value.replace(",", ".").toDouble(),
        client = Client(
            id = clientId,
            name = name,
            photo = photo,
            biography = biography
        ),
        dateTime = parseStringToDateTime(dateHour),
        details = CleaningDetails(
            roomsQuantity = rooms.map { it.toRoomQuantity() },
            questions = questions.map { it.toQuestion() },
            observations = observation
        ),
        type = statusService.map { it.toTypeEnum() },
        address = address.toAddress(),
        status = statusService.map { it.toServiceStatus() }
    )
}
fun StatusService.toServiceStatus(): br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceStatus {
    val status =  br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService.values()
        .find { it.description == this.status }
        ?: br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService.EM_ANDAMENTO

    return  ServiceStatus(
        type = status,
        dateTime = parseStringToDateTime(data_hora)
    )
}