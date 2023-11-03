package br.senai.sp.jandira.limpeanapp.ui.features.cleaning.data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.senai.sp.jandira.limpeanapp.core.domain.models.Question
import br.senai.sp.jandira.limpeanapp.core.domain.models.RoomQuantity
import br.senai.sp.jandira.limpeanapp.core.domain.models.TypeCleaning
import br.senai.sp.jandira.limpeanapp.core.domain.models.roomTypes
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.AboutClientState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.AddressCleaningState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningDetailsState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.CleaningSupportState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.ClientInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.HomeInfoState
import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.PrimordialInfoState

val fakePrimordialInfoState = PrimordialInfoState(
    price = 20.0,
    date = "18 de Abril de 2023",
    startTime = "10:00"
)
val fakeAddressCleaning = AddressCleaningState(
    street = "Rua das flores",
    state = "São Paulo",
    district = "Jardim Ipanema",
    complement = null,
    city = "Cotia"
)
val fakeClientInfo = ClientInfoState(
    name  = "Giovani",
    assentment = 10.0
)
val fakeHomeInfo = HomeInfoState(
    name = "Principal",
    typeHouse = "Casa"
)
val fakeAboutClientInfo = AboutClientState(
    clientInfo = fakeClientInfo,
    homeInfo = fakeHomeInfo
)
val fakeQuestions = listOf(
    Question(
        question = "Há crianças na casa?",
        answer = false,
    ),
    Question(
        question = "Há animais em sua casa?",
        answer = true,
    )
)

val fakeQuantityRooms = listOf(
    RoomQuantity(
        roomTypes[0],
        1
    ),
    RoomQuantity(
        roomTypes[3],
        2
    ),
    RoomQuantity(
        roomTypes[2],
        2
    )
)
val fakeCleaningSupport = CleaningSupportState(
    rooms = fakeQuantityRooms,
    questions = fakeQuestions,
    typeCleaning = TypeCleaning.DEFAULT
)
val fakeCleaningDetail = CleaningDetailsState(
    primordialInfo = fakePrimordialInfoState,
    addressCleaning = fakeAddressCleaning,
    aboutClientInfo = fakeAboutClientInfo,
    cleaningSupport = fakeCleaningSupport
)

class CleaningDetailsPreview : PreviewParameterProvider<CleaningDetailsState> {
    override val values = sequenceOf(
        fakeCleaningDetail
    )
}
class CleaningSupportPreview : PreviewParameterProvider<CleaningSupportState> {
    override val values = sequenceOf(
        fakeCleaningSupport
    )
}
class QuestionsPreview : PreviewParameterProvider<List<Question>> {
    override val values = sequenceOf(
        fakeQuestions
    )
}
class AboutClientPreview : PreviewParameterProvider<AboutClientState> {
    override val values = sequenceOf(
        fakeAboutClientInfo
    )
}