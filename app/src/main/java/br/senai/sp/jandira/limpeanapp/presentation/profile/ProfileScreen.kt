package br.senai.sp.jandira.limpeanapp.presentation.profile

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.domain.models.Assentment
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.core.domain.models.Phone
import br.senai.sp.jandira.limpeanapp.core.presentation.SinglePhotoPicker
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.components.textfield.NormalTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.createAddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.toDomain
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.LoadingDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.ConfirmDialog
import br.senai.sp.jandira.limpeanapp.presentation.features.profile.HeaderText
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun ProfileScreen(
    viewModel : ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.profile
    val diarist = state.diarist
    val profileDiarist = viewModel.profileDiarist
    val photoUrl = viewModel.photoUrl
    val isEditMode = state.isEditMode
    val context = LocalContext.current
    val isLoading = viewModel.updatedState.isLoading




    LaunchedEffect(viewModel.updatedState){
        if(viewModel.updatedState.message.isNotBlank()){
            Toast.makeText(
                context,
                viewModel.updatedState.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    if(state.isLoading){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator()
        }
    }

    if(isLoading){
        LoadingDialog()
    }
    ProfileScreen(
        photoUrl = photoUrl,
        state = profileDiarist,
        onEvent = viewModel::onEvent,
        isEditMode = isEditMode
    )

    LaunchedEffect(key1 = state.error){
        if (state.error?.isNotBlank() == true){
            Toast.makeText(context , state.error, Toast.LENGTH_LONG).show()
        }
    }


}



//@Preview
//@Composable
//fun TesteProfileScreen() {
//    LimpeanAppTheme {
//        ProfileScreen(
//            ProfileState(
//                profileDiarist = ProfileDiarist()
//            )
//        )
//    }
//}
@Composable
private fun ProfileScreen(
    photoUrl : String,
    state : ProfileDiarist,
    onEvent : (ProfileDiaristEvent) -> Unit,
    isEditMode: Boolean = false
) {

    val lazyState = rememberLazyListState()
    val context = LocalContext.current

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }
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
            SinglePhotoPicker(
                onSaveUri ={uri ->
                   onEvent(ProfileDiaristEvent.OnPhotoSelected(uri,context))
                },
                onSaveUrl = {url ->
                    onEvent(ProfileDiaristEvent.PhotoChanged(url))
                },

                url = photoUrl,
                diaristName = state.name,
                onEdit = isEditMode,
            )
            SeeProfileForm(
                state = state,
                onNameChanged = {
                    onEvent(ProfileDiaristEvent.NameChanged(it))
                },
                onCpfChanged = {
                     onEvent(ProfileDiaristEvent.CpfChanged(it))
                },
                onEmailChanged = {
                    onEvent(ProfileDiaristEvent.EmailChanged(it))
                },
                isEditMode = isEditMode
            )
            SeeGender(
                gender = state.gender,
                onGenderChange = {
                   onEvent(ProfileDiaristEvent.GenderChanged(it))
                },
                isEditMode = isEditMode
            )
        }
        item {
            SeeBiography(
                biography = state.biography,
                onBiographyChange = {
                    onEvent(ProfileDiaristEvent.BiographyChanged(it))
                },
                isEditMode = isEditMode
            )
        }
        item {
            EditProfileActions(
                isEditMode = isEditMode,
                onSave = {onEvent(ProfileDiaristEvent.OnSaveUpdated(context))},
                onEdit = {onEvent(ProfileDiaristEvent.OnEditMode)},
                onCancel = {onEvent(ProfileDiaristEvent.OnCancelUpdated)},
            )
        }
        item {
            PhoneSection(
                phones = state.phones
            )
        }
        item {
            HeaderText(
                title = "Endereço",
                style = MaterialTheme.typography.titleMedium)
        }
        item{
            SeeAddress(
                address = state.address,
                onAddressChange = {
                     onEvent(ProfileDiaristEvent.AddressFetchedChanged(it))
                },
                onCepChanged = {
                    onEvent(ProfileDiaristEvent.CepChanged(it))

                },
                onNumberChanged = {
                    onEvent(ProfileDiaristEvent.NumberChanged(it))

                }
            )
        }


    }
    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            lazyState.animateScrollToItem(lazyState.firstVisibleItemScrollOffset)
            scrollToTop = false
        }
    }


}

@Composable
fun EditProfileActions(
    isEditMode: Boolean,
    onSave : () -> Unit,
    onEdit : () -> Unit,
    onCancel : () -> Unit,
) {


    var firstAction by remember {
        mutableStateOf("")
    }

    firstAction = if(isEditMode){
        "Salvar"
    } else {
        "Editar Perfil"
    }
    val onAction = if(isEditMode){
        onSave
    } else{
        onEdit
    }

    Row {
        Button(onClick = onAction){
            Text(text = firstAction)
        }
        if(isEditMode){
            Button(onClick = onCancel) {
                Text(text = "Cancelar")
            }
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



@Composable
fun SeeAddress(
    address: Address = fakeAdressTest,
    onAddressChange : (Address) -> Unit,
    onCepChanged : (String) -> Unit,
    onNumberChanged : (String) -> Unit,

    ) {


    var isEditMode by remember {
        mutableStateOf(false)
    }
    var state by remember {
        mutableStateOf(createAddressFormState(address = address))
    }

    var isShowDialog by remember {
        mutableStateOf(false)
    }

    ListItem(
        headlineContent = {
            Text(text = "${address.street}, ${address.number}")
        },
        supportingContent = { Text(text = "${address.cep} - ${address.city}, ${address.state}") },
        trailingContent = {
            Button(
                onClick = {
                       isEditMode = true
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
            }}
    )
    if(isEditMode){
        Dialog(onDismissRequest = {isEditMode = false}) {
            Column(
                Modifier
                    .padding(24.dp)
                    .fillMaxSize()) {
                AddressFormUi(
                    state = state,
                    onEvent = {event ->
                        when (event){
                            is AddressFormEvent.CepChanged -> {
                                onCepChanged(event.newCep)
                            }

                            is AddressFormEvent.NumberChanged -> {
                                onNumberChanged(event.newNumber)
                            }
                            is AddressFormEvent.ReceivedCepFromApi -> {
                                onAddressChange(event.completeAddress.toDomain())
                            }

                            else -> {}
                        }
                    })
                Button(onClick = {
                    isShowDialog = true
                }) {
                    Text(text = "Salvar")
                }
            }

        }
    }
    if(isShowDialog){
        ConfirmDialog(
            onDismissRequest = {isShowDialog = false},
            onConfirmPress = {
                onAddressChange(state.toDomain())
                isShowDialog = false
            },
            onCancelPress = {isShowDialog = false}
        )
    }



}


@Composable
fun SeeBiography(
    biography : String?,
    isEditMode: Boolean = false,
    onBiographyChange : (String) -> Unit
) {

    var biographyState by remember {
        mutableStateOf(biography?: "")
    }

    ProfileSection(name = "Biografia") {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = biography?: "",
            onValueChange = {
                onBiographyChange(it)
            },
            readOnly = !isEditMode,
            placeholder = {
                if(biography == null){
                    Text(text = "Sem biografia.")
                }
            }
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
    onGenderChange : (Gender) -> Unit,
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
                        onGenderChange(it)
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
    state : ProfileDiarist,
    onNameChanged : (String) -> Unit,
    onCpfChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    isEditMode: Boolean = false
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        NormalTextField(
            labelText = "Nome",
            value = state.name,
            onValueChange = { onNameChanged(it) },
            inEditMode = isEditMode
        )
        NormalTextField(
            labelText = "Cpf (apenas dígito)",
            value = state.cpf,
            onValueChange = {  onCpfChanged(it) },
            keyboardType = KeyboardType.Number,
            inEditMode = isEditMode
        )
        NormalTextField(
            labelText = "Email",
            value = state.email,
            onValueChange = {  onEmailChanged(it)},
            keyboardType = KeyboardType.Email,
            inEditMode = isEditMode
        )
    }

}
