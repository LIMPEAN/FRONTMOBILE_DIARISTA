package br.senai.sp.jandira.limpeanapp.core.data.mapper

import android.app.Service
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OtherHouses
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.AddressDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.QuestionDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.RoomDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.ServiceDto
import br.senai.sp.jandira.limpeanapp.core.data.remote.dto.StatusServiceDto
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.models.Client
import br.senai.sp.jandira.limpeanapp.core.domain.models.HomeAddress
import br.senai.sp.jandira.limpeanapp.core.domain.models.Question
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomType

import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceStatus
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum
import br.senai.sp.jandira.limpeanapp.core.domain.models.obterTipoDeLimpeza
import br.senai.sp.jandira.limpeanapp.core.domain.models.roomTypes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun ServiceDto.toCleaning(): Cleaning {
    val status = statusService.map { it.toServiceStatus() }

    return Cleaning(
        id = serviceId,
        price = value.toDoubleOrNull() ?: 0.0,
        client = Client(
            name = this.name,
            photo = this.photo,
            biography = this.biography,
        ),
        dateTime = parseStringToDateTime(dateHour),
        type = obterTipoDeLimpeza(this.typeClean)?: TypeCleaningEnum.PADRAO,
        status = status,
        address = Address(
            cep = address.cep,
            street = address.publicPlace,
            district =address.district,
            city = address.city,
            state = address.state,
            number = address.houseNumber,
            complement = address.complement
        ),
        details = CleaningDetails(
            questions = question.map { it.toQuestion() },
            roomsQuantity = room.map { it.toRoomQuantity() }
        )
    )
}
fun parseStringToDateTime(dateTimeString: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    return LocalDateTime.parse(dateTimeString, formatter)
}
fun StatusServiceDto.toServiceStatus(): ServiceStatus {
    val type = StatusService.values().find { it.description == status }
    Log.i("TYPE", type.toString())
    return ServiceStatus(
        type = type?: StatusService.EM_ABERTO,
        dateTime = parseStringToDateTime(dateTime)
    )
}

fun QuestionDto.toQuestion(): Question {
    return Question(
        question = asks,
        answer = answer
    )
}

fun RoomDto.toRoomQuantity(): RoomQuantity {
    val roomType = roomTypes.find { it.name == name }

    if(roomType != null){
        return if(quantity != 0){
            RoomQuantity(
                roomType = roomType,
                quantity = quantity
            )
        } else {
            RoomQuantity(
                roomType = roomType,
                quantity = null
            )
        }
    } else{
        return RoomQuantity(
            roomType = RoomType(
                icon =Icons.Default.OtherHouses,
                name = name
            ),
            quantity = quantity
        )
    }
}

fun AddressDto.toHomeAddress(): HomeAddress {
    return HomeAddress(
        state = state,
        city = city,
        district = district,
        cep = cep,
        complement = complement,
        publicPlace = publicPlace,
        numberHouse = houseNumber
    )
}