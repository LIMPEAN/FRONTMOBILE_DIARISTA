package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.PrimordialInfoState
import java.time.LocalDate
import java.time.LocalDateTime

data class Cleaning(
    val id : Number? = null,
    val price : Double = 0.0,
    val client : Client = Client(),
    val dateTime: LocalDateTime = LocalDateTime.now(),
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

enum class StatusService(val codigo: Number, val descricao: String) {
    EM_ABERTO(1, "Em aberto"),
    AGENDADO(2, "Agendado"),
    EM_ANDAMENTO(3, "Em andamento"),
    FINALIZADO(4, "Finalizado"),
    CANCELADO(5, "Cancelado")
}


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
            date = this.dateTime.toString()
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

enum class Meses(val nome: String) {
    JANEIRO("Janeiro"),
    FEVEREIRO("Fevereiro"),
    MARCO("Março"),
    ABRIL("Abril"),
    MAIO("Maio"),
    JUNHO("Junho"),
    JULHO("Julho"),
    AGOSTO("Agosto"),
    SETEMBRO("Setembro"),
    OUTUBRO("Outubro"),
    NOVEMBRO("Novembro"),
    DEZEMBRO("Dezembro")
}
fun obterNomeDoMes(numeroDoMes: Int): String? {
    return Meses.values().find { it.ordinal + 1 == numeroDoMes }?.nome
}