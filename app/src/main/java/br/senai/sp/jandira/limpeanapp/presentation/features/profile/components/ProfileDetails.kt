package br.senai.sp.jandira.limpeanapp.presentation.features.profile.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.core.domain.models.Phone
import br.senai.sp.jandira.limpeanapp.core.domain.models.toAddressCleaningState
import br.senai.sp.jandira.limpeanapp.core.presentation.SinglePhotoPicker
import br.senai.sp.jandira.limpeanapp.core.presentation.components.text.NormalTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormUi
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.data.fakeAddressCleaning
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.HeaderText
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.compose.LimpeanAppTheme
import java.time.LocalDate

@Composable
fun ProfileDetails(
    diarist : Diarist
) {

    var isEditMode by remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            title = "Perfil",
            style = MaterialTheme.typography.titleMedium
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
//            SinglePhotoPicker(onSaveUri = {}, onSaveUrl = {})
//            SeeProfileForm(
//                state = ProfileFormState(
//                    name = diarist.name,
//                    cpf = diarist.cpf,
//                    email = diarist.email,
//                ),
//                onEvent = {
//
//                }
//            )
//            SeeGender(
//                gender = diarist.gender,
//                isEditMode = isEditMode
//            )
//            SeeBiography(
//                biography = diarist.biography,
//                isEditMode = isEditMode
//            )
//            SeeAddress()
            SeeAssentments()
            SeePhones(
                phones = diarist.phones,
                onEvent = {

                }
            )
        }
        Row {
            Button(onClick = {isEditMode = true}) {
                Text(text = "Editar")
            }
        }

    }
}

@Composable
fun SeeAssentments(
    assentments : List<Assentment>
) {
    ProfileSection(name = "Avaliações") {
        LazyRow(){
            items(assentments){assentment ->
                Card {
                    AsyncImage(
                        model = assentment.,
                        contentDescription = )
                }
            }
        }
    }
}


@Composable
fun SeeAddress(
    address: Address = Address(),
    isLoading : Boolean = false,
    isEditMode: Boolean = false
) {

    ProfileSection(name = "Endereço") {
        AddressFormUi(
            state = AddressFormState(
                cep = address.cep,
                bairro = address.district,
                cidade = address.city,
                estado = address.state,
                logradouro = address.street,
                numero = address.number,
                isLoading = isLoading
            ),
            isEditMode = isEditMode,
            onEvent = {},
            modifier = Modifier
        )
    }


}


@Composable
fun SeeBiography(
    biography : String?,
    isEditMode: Boolean = false
) {

    var biographyState by remember {
        mutableStateOf(biography?: "")
    }

    ProfileSection(name = "Biografia") {
        OutlinedTextField(
            value = biographyState,
            onValueChange = {
                biographyState = it
            },
            readOnly = isEditMode
        )
    }

}

@Composable
fun ProfileSection(
    name:  String,
    items : @Composable () -> Unit
) {
    HeaderText(
        title = name,
        style = MaterialTheme.typography.titleMedium)
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items()
    }
}
@Composable
fun SeeGender(
    gender : Gender,
    isEditMode : Boolean  = false
) {

    val genders = Gender.values()
    var selected by remember {
        mutableStateOf(gender)
    }


    HeaderText(
        title = "Gênero",
         style = MaterialTheme.typography.titleMedium)
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        genders.map {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == it,
                    onClick ={
                        selected = it
                    },
                    enabled = isEditMode,
                )
                Text(
                    text = it.nome,
                )
            }


        }
    }
}

//@Composable
//fun SeeAddres() {
//
//}
@Composable
fun SeePhoto(
    photoUrl: String?,
    description: String
) {
    val painter = rememberAsyncImagePainter(model = photoUrl)

    Box(modifier = Modifier.size(200.dp)) {
        if (painter.state is AsyncImagePainter.State.Error || painter.state is AsyncImagePainter.State.Empty || photoUrl == null) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Image Profile Example",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
        } else {
            AsyncImage(
                model = photoUrl,
                contentDescription = description,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
@Composable
fun SeePhones(
    phones : List<Phone>,
    onEvent: (ProfileFormEvent) -> Unit,
    inEditPhone : Boolean = false
) {

    HeaderText(
        title = "Telefones",
        style = MaterialTheme.typography.titleMedium

    )
    LazyColumn{
        items(phones){state ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NormalTextField(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    labelText = "DDD",
                    value = state.ddd,
                    onValueChange = { onEvent(ProfileFormEvent.DDDChanged(it)) },
                    keyboardType = KeyboardType.Phone,
                    inEditMode = inEditPhone
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                NormalTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    labelText = "Telefone",
                    value = state.number,
                    onValueChange = { onEvent(ProfileFormEvent.PhoneChanged(it)) },
                    keyboardType = KeyboardType.Phone,
                    inEditMode = inEditPhone
                )
            }
        }
    }
}

@Composable
fun SeeProfileForm(
    state : ProfileFormState,
    onEvent : (ProfileFormEvent) -> Unit,
    editMode: Boolean = false
) {
    NormalTextField(
        labelText = "Nome",
        value = state.name,
        onValueChange = { onEvent(ProfileFormEvent.NameChanged(it)) },
        inEditMode = editMode

    )
    NormalTextField(
        labelText = "Cpf (apenas dígito)",
        value = state.cpf,
        onValueChange = { onEvent(ProfileFormEvent.CpfChanged(it)) },
        keyboardType = KeyboardType.Number,
        inEditMode = editMode
    )
    NormalTextField(
        labelText = "Email",
        value = state.email,
        onValueChange = { onEvent(ProfileFormEvent.EmailChanged(it)) },
        keyboardType = KeyboardType.Email,
        inEditMode = editMode
    )
}
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name ="Dark Mode")
@Composable
fun ProfileDetailsPreview() {
    LimpeanAppTheme {
        ProfileDetails(Diarist(
            name = "John",
            cpf = "987.654.321-00",
            phones = listOf(Phone("11", "945645645"), Phone("11", "965312185")),
            email = "john.custom@example.com",
            password = "customPassword123",
            dateOfBirth = LocalDate.of(1985, 5, 10),
            photo = "customPhoto.jpg",
            gender = Gender.MASCULINO,
            biography = "\"João Silva, nascido em uma pequena cidade costeira, sempre teve uma paixão por explorar o mundo ao seu redor. Desde jovem, ele demonstrou curiosidade e entusiasmo por aprender coisas novas. Crescendo em um ambiente acolhedor, João desenvolveu um amor pela natureza e uma conexão especial com o oceano.\n" +
                    "\n",
            address = listOf(Address("456 Custom St", "Townville", "Custom State", "54321")),
            assentments = listOf(
                Assentment("Custom Task 1", personEvaluatedId = 123, star = 4),
                Assentment("Custom Task 2", personEvaluatedId = 123, star = 5)
            ),
            id = 123
        ))
    }
}