package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.PrimordialInfoState
import java.time.LocalDate

data class Cleaning(
    val id : Number? = null,
    val price : Double = 0.0,
    val client : Client = Client(),
    val date: LocalDate = LocalDate.now(),
    val type : TypeCleaning = TypeCleaning.DEFAULT,
    val status : List<ServiceStatus> = emptyList(),
    val address : Address = Address(),
    val details : CleaningDetails = CleaningDetails()
)

data class CleaningDetails(
    val questions : List<Question> = emptyList(),
    val roomsQuantity : List<RoomQuantity> = emptyList()
)
data class Question(
    val question : String,
    val answer : Boolean,
)
data class ServiceStatus(
    val name: String,
    val dateTime : String
)

enum class TypeCleaning(val inPortuguese: String) {
    DEFAULT(inPortuguese = "Padrão")
}
fun Cleaning.toCleaningCardState() : CleaningCardState {
    return CleaningCardState(
        id = this.id?: 0,
        servicePrice = this.price,
        local = this.address.inCleaningCard(),
        nameClient = this.client.name,
        quantityRooms = this.details.roomsQuantity
    )
}

fun Cleaning.toDetailsState() : CleaningDetailsState {
    return CleaningDetailsState(
        primordialInfo = PrimordialInfoState(
            price = this.price,
            startTime = "Test",
            date = this.date.toString()
        ),
        addressCleaning = this.address.toAddressCleaningState(),
        aboutClientInfo = this.client.toAboutClientState(),
        cleaningSupport = CleaningSupportState(
            typeCleaning = this.type,
            questions = this.details.questions,
            rooms = this.details.roomsQuantity
        )

    )
}