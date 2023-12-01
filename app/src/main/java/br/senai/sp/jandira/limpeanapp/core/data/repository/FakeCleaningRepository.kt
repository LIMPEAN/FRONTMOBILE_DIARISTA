package br.senai.sp.jandira.limpeanapp.core.data.repository

import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Cleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.CleaningDetails
import br.senai.sp.jandira.limpeanapp.core.domain.models.Client
import br.senai.sp.jandira.limpeanapp.core.domain.models.Question
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.ServiceStatus
import br.senai.sp.jandira.limpeanapp.core.domain.models.StatusService
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaningEnum
import br.senai.sp.jandira.limpeanapp.core.domain.models.roomTypes
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


val fakeCleanings = listOf(
    Cleaning(
        1,
        client = Client(
            name ="Giovani",
            biography = null,
            photo = "minhafoto"
        ),
        status = listOf(
            ServiceStatus(StatusService.EM_ANDAMENTO, dateTime = LocalDateTime.now()),
            ServiceStatus(StatusService.EM_ABERTO, LocalDateTime.now())
        ),
        address = Address("06703480", city = "New York", complement = null, district = "teste" , number = "14", state = "New York", street = "St. Mark’s Place"),
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
            ),
            observations = "Serviço para as férias"
        ),
        dateTime = LocalDateTime.now(),
        price = 450.00,
        type = listOf(
            TypeCleaningEnum.PADRAO
        )
    ),
    Cleaning(
        2,
        client = Client(
            name = "Jennifer",
            biography = "Olá meu nome é Jennifer",
            photo = "minhafoto1"
        ),
        status = listOf(
            ServiceStatus(StatusService.FINALIZADO, dateTime = LocalDateTime.now()),
            ServiceStatus(StatusService.AGENDADO, dateTime = LocalDateTime.now())
        ),
        address = Address("29145480", city = "Genebra", complement = null, district = "teste" , number = "10", state = "Suíca", street = "Rua dos Jardins"),
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
        type = listOf(
            TypeCleaningEnum.PADRAO
        ),
        dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(10,12))
    ),
    Cleaning(
        3,
        client = Client(
            name ="Giovani",
            biography = null,
            photo = "minhafoto"
        ),
        status = listOf(
            ServiceStatus(StatusService.EM_ANDAMENTO, dateTime = LocalDateTime.now()),
            ServiceStatus(StatusService.EM_ABERTO, LocalDateTime.now())
        ),
        address = Address("06703480", city = "New York", complement = null, district = "teste" , number = "14", state = "New York", street = "St. Mark’s Place"),
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
            ),
            observations = "Serviço para as férias"
        ),
        dateTime = LocalDateTime.now(),
        price = 0.0,
        type = listOf(
            TypeCleaningEnum.PADRAO
        )
    ),
)


//class FakeCleaningRepository (
//  private var listCleaning : List<Cleaning> = emptyList()
//) : CleaningRepository {
//
//    init {
//        listCleaning = fakeCleanings
//    }
//    override fun getScheduledCleanings(): Flow<List<Cleaning>> {
//        return flow {
//            emit(listCleaning)
//        }
//    }
//
//    override suspend fun getCleaningDetail(id: Number): Cleaning? {
//       return listCleaning.find { it.id == id }
//    }
//
//}