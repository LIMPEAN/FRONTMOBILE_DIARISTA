package br.senai.sp.jandira.limpeanapp.core.data.remote.dto

import com.google.gson.annotations.SerializedName


data class OpenServicesDto(
    val status: Number,
    val data : List<ServiceItem>
)
data class ServiceItem(
    val service : ServiceDto
)

data class ServiceDto(
    val serviceId: Int,
    @SerializedName("status_service") val statusService: List<StatusServiceDto>,
    val name: String,
    val photo: String,
    val biography: String?,
    @SerializedName("type_clean") val typeClean: String,
    @SerializedName("date_hour") val dateHour: String,
    @SerializedName("obeservation") val observation: String,
    val tasks: String,
    val value: String,
    val question: List<QuestionDto>,
    val room: List<RoomDto>,
    val address: AddressDto
)

data class StatusServiceDto(
    val status: String,
    @SerializedName("data_hora") val dateTime: String
)

data class QuestionDto(
    val asks: String,
    val answer: Boolean
)

data class RoomDto(
    val name: String,
    val quantity: Int
)


