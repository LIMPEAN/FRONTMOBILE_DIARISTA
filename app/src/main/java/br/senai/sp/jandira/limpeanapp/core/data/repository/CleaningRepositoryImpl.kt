package br.senai.sp.jandira.limpeanapp.core.data.repository

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.vector.ImageVector
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.feature_authentication.domain.models.Address
import br.senai.sp.jandira.limpeanapp.home.data.repository.cleanings
import br.senai.sp.jandira.limpeanapp.home.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.home.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.home.domain.models.Client
import br.senai.sp.jandira.limpeanapp.home.domain.models.Question
import br.senai.sp.jandira.limpeanapp.home.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.home.domain.models.RoomType
import br.senai.sp.jandira.limpeanapp.home.domain.models.ServiceStatus
import br.senai.sp.jandira.limpeanapp.home.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.home.domain.models.roomTypes
import br.senai.sp.jandira.limpeanapp.home.domain.repository.CleaningRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject


val fakeCleanings = listOf(
    Cleaning(
        1,
        client = Client(
            "Giovani",
            biography = null,
            photo = "minhafoto"
        ),
        status = listOf(
            ServiceStatus("A iniciar", dateTime = LocalDate.now().toString())
        ),
        address = Address("10001", city = "New York", complement = null, district = "teste" , number = "14", state = "New York", street = "St. Mark’s Place"),
        details = CleaningDetails(
            questions = listOf(
                Question(
                    "Há crianças na casa?",
                    answer = false,
                ),
                Question("Há animais na casa?", answer = true)
            ),
            roomsQuantity = listOf(
                RoomQuantity(roomTypes[0], 2),
                RoomQuantity(roomTypes[1], 1),
                RoomQuantity(roomTypes[2], 1),
                RoomQuantity(roomTypes[3], 1)
            )
        ),
        date = LocalDate.now(),
        price = 450.00,
        type = TypeCleaning.DEFAULT
    ),
    Cleaning(
        2,
        client = Client(
            "Jennifer",
            biography = "Olá meu nome é Jennifer",
            photo = "minhafoto1"
        ),
        status = listOf(
            ServiceStatus("Terminando", dateTime = LocalDate.now().toString())
        ),
        address = Address("10001", city = "Genebra", complement = null, district = "teste" , number = "10", state = "Suíca", street = "Rua dos Jardins"),
        details = CleaningDetails(
            questions = listOf(
                Question(
                    "Há crianças na casa?",
                    answer = false,
                ),
                Question("Há animais na casa?", answer = true)
            ),
            roomsQuantity = listOf(
                RoomQuantity(roomTypes[0], 2),
                RoomQuantity(roomTypes[1], 1),
                RoomQuantity(roomTypes[2], 1),
                RoomQuantity(roomTypes[3], 1),
                RoomQuantity(roomTypes[4], 1)
            )
        ),
        price = 300.00,
        type = TypeCleaning.DEFAULT,
        date = LocalDate.of(2020,12,1)
    )
)


class CleaningRepositoryImpl (
  private var listCleaning : List<Cleaning> = emptyList()
) : CleaningRepository {

    init {
        listCleaning = fakeCleanings
    }
    override fun getScheduledCleanings(): Flow<List<Cleaning>> {
        return flow {
            emit(listCleaning)
        }
    }

    override suspend fun getCleaningDetail(id: Number): Cleaning? {
       return listCleaning.find { it.id == id }
    }

}