package br.senai.sp.jandira.limpeanapp.core.domain.models

import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.PrimordialInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningCardState
import java.time.LocalDateTime

data class Cleaning(
    val id : Number? = null,
    val price : Double = 0.0,
    val client : Client = Client(),
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val type : TypeCleaningEnum = TypeCleaningEnum.PADRAO,
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
    val type : StatusService,
    val dateTime : LocalDateTime
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
enum class TypeCleaningEnum(val code: Int, val nameApi: String) {
    COMERCIAL(1, "Comercial"),
    PADRAO(2, "Padrão"),
    POS_OBRA(4, "Pós obra"),
    PRE_MUDANCA(5, "Pré mudança"),
    PRE_OBRA(3, "Pré obra")
}
fun obterTipoDeLimpeza(nomeDoTipo : String) : TypeCleaningEnum?{
    return TypeCleaningEnum.values().find { it.nameApi == nomeDoTipo }
}