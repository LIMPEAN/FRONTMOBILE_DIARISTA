package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.components.AuthContainer
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.data.repository.DiaristFakeRepository
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.createAddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.createProfileFormState
import com.example.compose.LimpeanAppTheme
import java.time.LocalDate




@Composable
fun RegisterScreenController(){

    val registerState = rememberRegisterState()

    val modifierForm = Modifier
        .fillMaxHeight(0.7f)
        .padding(20.dp)

    val navController = rememberNavController()
    RegisterScreen(
        state = diaristTest,
        titleSection = {
            TitleSection(title = registerState.title, description = registerState.description)
        },
        onEvent = {},
        onButtonClick = {



        },
        titleButton = registerState.titleButton
   ){
        NavHost(
           startDestination = "Profile",
           navController = navController
       ){
           composable("Profile"){
               val profileFormState = createProfileFormState(diaristTest)
               ProfileFormUi(
                   profilePhoto = { /*TODO*/ },
                   state = profileFormState,
                   onEvent = {}
               )
           }
           composable("Address"){
               val addressFormState = createAddressFormState(diaristTest.address)
               AddressFormUi(
                   state = addressFormState,
                   onChangeNumber = {

                   },
                   onChangeCep = {

                   },
                   modifier = modifierForm
               )
           }

       }
   }

}




@Composable
fun RegisterScreen(
    state : Diarist,
    titleSection: @Composable ()-> Unit,
    onEvent : (RegisterEvent) -> Unit,
    onButtonClick: () -> Unit,
    titleButton: String,
    form: @Composable (Diarist)-> Unit,
) {



    val scrollState = rememberScrollState()

    AuthContainer(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        titleSection()

        form(state)

        Button(onClick = { onButtonClick() } ){
            Text(text = titleButton)
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun RegisterProfilePreview() {

    var profileFormState by remember{
        mutableStateOf(
            createProfileFormState(diaristTest)
        )
    }

    LimpeanAppTheme {
        RegisterScreen(
            state = diaristTest,
            titleSection = {
                TitleSection(
                    horizontal = Alignment.CenterHorizontally,
                    title = stringResource(R.string.register_title),
                    description = stringResource(R.string.register_description)
                )
            },
            onEvent = {},
            onButtonClick = {},
            titleButton = "Próxima parte"
        ) {
            ProfileFormUi(
                profilePhoto = { /*TODO*/ },
                state = profileFormState,
                onEvent = {}
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RegisterAddressPreview() {

    var addressFormState by remember {
        mutableStateOf(
            createAddressFormState(diaristTest.address)
        )
    }

    LimpeanAppTheme {
        RegisterScreen(
            state = diaristTest,
            onEvent = {},
            onButtonClick = {},
            titleSection = {
                TitleSection(
                    horizontal = Alignment.CenterHorizontally,
                    title = stringResource(R.string.register_address_title),
                    description = stringResource(R.string.register_address_description)
                )
            },
            titleButton = "Finalizar"
        ) {
            AddressFormUi(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .padding(20.dp),
                state = addressFormState,
                onChangeNumber = {},
                onChangeCep = {}
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {

    LimpeanAppTheme {
        RegisterScreen(
            state = diaristTest,
            titleSection = {
                TitleSection(
                    horizontal = Alignment.CenterHorizontally,
                    title = stringResource(R.string.register_title),
                    description = stringResource(R.string.register_description)
                )
            },
            onButtonClick = {},
            onEvent = {},
            titleButton = "Próxima parte"
        ){
            Text(text = "form")
        }

    }
}

val diaristTest = Diarist(
    name = "",
    cpf = "",
    ddd = "",
    phone = "",
    email = "",
    password = "",
    dateOfBirth = LocalDate.of(2000,10,10),
    photo = null,
    gender = Gender.FEMININO,
    biography = "",
    address = Address(),
    id = 0
)
