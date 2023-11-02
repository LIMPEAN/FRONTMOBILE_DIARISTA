package br.senai.sp.jandira.limpeanapp.home.domain.models

import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Address
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.AddressCleaningState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningCardState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.home.presentation.uses.cleaning.components.PrimordialInfoState
import java.time.LocalDate

data class Cleaning(
    val id : Number?,
    val price : Double,
    val client : Client,
    val date: LocalDate,
    val type : TypeCleaning,
    val status : List<ServiceStatus>,
    val address : Address,
    val details : CleaningDetails
) {
    fun toCleaningCardState() : CleaningCardState {
        return CleaningCardState(
            id = this.id?: 0,
            servicePrice = this.price,
            local = this.address.inCleaningCard(),
            nameClient = this.client.name,
            quantityRooms = this.details.roomsQuantity
        )
    }
    fun toDetailsState() : CleaningDetailsState{
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

}


data class ServiceStatus(
    val name: String,
    val dateTime : String
)
enum class TypeCleaning(val inPortuguese: String) {
    DEFAULT(inPortuguese = "Padrão")
}
