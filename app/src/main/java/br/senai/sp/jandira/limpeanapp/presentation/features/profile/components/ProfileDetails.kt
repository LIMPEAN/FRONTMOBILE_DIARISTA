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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.core.domain.models.Phone
import br.senai.sp.jandira.limpeanapp.core.presentation.SinglePhotoPicker
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.components.textfield.NormalTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.login.LoginEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.createAddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormState
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.ConfirmDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.DiaristProfile
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.HeaderText
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.ProfileViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.compose.LimpeanAppTheme
import java.time.LocalDate


@Composable
fun ProfileDetails(
    viewModel : ProfileViewModel = hiltViewModel()
) {

    val state  = viewModel.state
    ProfileDetails(
        state = state
    )

}

@Composable
private fun ProfileDetails(
    state : DiaristProfile = DiaristProfile()
) {
    var isEditMode by remember {
        mutableStateOf(false)
    }
    val diarist = state.diarist
    val phones = diarist.phones
    var profileForm by remember {
        mutableStateOf(
            ProfileFormState(
                name = diarist.name,
                cpf = diarist.cpf,
                email = diarist.email,
            )
        )
    }


    val lazyState = rememberLazyListState()

    var scrollToTop by remember { mutableStateOf(false) }
    LazyColumn(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyState
    ) {
        item{
            HeaderText(
                title = "Perfil",
                style = MaterialTheme.typography.titleMedium
            )
        }
        item {
            SinglePhotoPicker(onSaveUri = {}, onSaveUrl = {}, url = diarist.photo?: "", diaristName = diarist.name)
            SeeProfileForm(
                state = profileForm,
                onEvent = {event ->
                    when(event){
                        is ProfileFormEvent.NameChanged -> {
                            profileForm = profileForm.copy(name = event.value)
                        }
                        is ProfileFormEvent.CpfChanged ->{
                            profileForm = profileForm.copy(cpf = event.value)
                        }
                        is ProfileFormEvent.EmailChanged ->{
                            profileForm = profileForm.copy(email = event.value)

                        }

                        else -> {}
                    }
                },
                editMode = isEditMode
            )
            SeeGender(
                gender = diarist.gender,
                isEditMode = isEditMode
            )
        }
        item {
            SeeBiography(
                biography = diarist.biography,
                isEditMode = isEditMode
            )
        }
        item {
            val label = if(isEditMode){ "Salvar" } else{ "Editar perfil"}
            Row {
                Button(onClick = {
                    isEditMode = !isEditMode
                    scrollToTop = true
                }) {
                    Text(text = label)
                }
            }
        }
        item {
            PhoneSection(phones = phones)
        }
        item {
            HeaderText(
                title = "Endereço(s)",
                style = MaterialTheme.typography.titleMedium)
        }
        item{
            SeeAddress(
                address = diarist.address,
                onEdit = {}
            )
        }


    }
    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            lazyState.scrollToItem(0)
            scrollToTop = false
        }
    }


}

@Composable
fun PhoneSection(
    phones: List<Phone> = listOf()
) {
    var selectedPhone by remember {
        mutableStateOf<Phone?>(null)
    }
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    var isShowEditPhone by remember {
        mutableStateOf(false)
    }
    SeePhones(
        phones = phones,
        onEdit = {
            selectedPhone = it
            isShowEditPhone = true
        },
    )
    selectedPhone?.let {
        EditPhone(
            phone = it,
            onSave = {
                isShowDialog = true
            }
        )
    }

    if(isShowDialog){
        ConfirmDialog(
            onDismissRequest = { isShowDialog = false},
            onConfirmPress = {
                isShowDialog = false
                //action
                selectedPhone = null
            },
            onCancelPress = { isShowDialog = false }
        )
    }

}
@Composable
fun EditPhone(
    phone : Phone = Phone(),
    onSave : () -> Unit = {},
    onCancel : () -> Unit = {},
) {
    var editPhone by remember {
        mutableStateOf(phone)
    }
    Dialog(onDismissRequest = onCancel) {
        Card(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NormalTextField(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    labelText = "DDD",
                    value = phone.ddd,
                    onValueChange = {
                        editPhone = editPhone.copy(ddd = it)
                    },
                    keyboardType = KeyboardType.Phone,
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                NormalTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    labelText = "Telefone",
                    value = phone.number,
                    onValueChange = {  editPhone = editPhone.copy(ddd = it) },
                    keyboardType = KeyboardType.Phone,
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onSave) {
                    Text(text = "Salvar")
                }
                Button(onClick = onCancel) {
                    Text(text = "Cancelar")
                }
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
//                Card {
//                    AsyncImage(
//                        model = assentmen,
//                        contentDescription = )
//                }
            }
        }
    }
}

val fakeAdressTest = Address(
    cep = "06703480",
    street = "Rua da amizade",
    number = "374",
    state = "SP"
)


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SeeAddressPreview() {
    LimpeanAppTheme {
        var selectedAdress by remember {
            mutableStateOf<Address?>(null)
        }
        SeeAddress(
            onEdit = {
                selectedAdress = it
            }
        )
        selectedAdress?.let {
            Dialog(onDismissRequest = {selectedAdress = null}) {
                AddressFormUi(
                    state = createAddressFormState(it),
                    onEvent = {},
                    modifier = Modifier
                )
            }
        }

    }
}
@Composable
fun SeeAddress(
    address: List<Address> = listOf(fakeAdressTest,fakeAdressTest),
    isLoading : Boolean = false,
    isEditMode: Boolean = false,
    onEdit : (Address) -> Unit
) {

    Column {
        address.map {oneAdress ->
            ListItem(
                headlineContent = {
                    Text(text = "${oneAdress.street}, ${oneAdress.number}")
                },
                supportingContent = { Text(text = "${oneAdress.cep} - ${oneAdress.city}, ${oneAdress.state}") },
                trailingContent = {
                    Button(
                        onClick = {
                            onEdit(oneAdress)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                }}
            )
        }
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
            readOnly = !isEditMode
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
    onEdit : (Phone) -> Unit
) {

    HeaderText(
        title = "Telefones",
        style = MaterialTheme.typography.titleMedium

    )
    //
    Column{
        phones.map {phone->
            ListItem(
                headlineContent = {
                    Text(text =  "(${phone.ddd}) ${phone.number}")
                },
                trailingContent = {
                    Button(
                        onClick = {
                            onEdit(phone)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                    }}
            )
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                NormalTextField(
//                    modifier = Modifier.fillMaxWidth(0.3f),
//                    labelText = "DDD",
//                    value = state.ddd,
//                    onValueChange = { onEvent(ProfileFormEvent.DDDChanged(it)) },
//                    keyboardType = KeyboardType.Phone,
//                    inEditMode = inEditPhone
//                )
//                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
//                NormalTextField(
//                    modifier = Modifier.fillMaxWidth(1f),
//                    labelText = "Telefone",
//                    value = state.number,
//                    onValueChange = { onEvent(ProfileFormEvent.PhoneChanged(it)) },
//                    keyboardType = KeyboardType.Phone,
//                    inEditMode = inEditPhone
//                )
//            }
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
        ProfileDetails(
            state = DiaristProfile(
                diarist = Diarist(
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
                    address = listOf(
                        Address(
                            "456 Custom St",
                            "Townville",
                            "Custom State",
                            "54321"
                        )
                    ),
                    assentments = listOf(
                        Assentment("Custom Task 1", personEvaluatedId = 123, star = 4),
                        Assentment("Custom Task 2", personEvaluatedId = 123, star = 5)
                    ),
                    id = 123
                )
            )
        )
    }
}