package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.core.AuthenticationRoute
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.components.AuthContainer
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.address.createAddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormEvent
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.ProfileFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile.createProfileFormState

@Composable
fun RegistrationNav(
    navController: NavHostController,
    diarist: Diarist,
    onChangeAddress: (AddressFormEvent) -> Unit,
    onChangeProfile: (ProfileFormEvent) -> Unit
) {
    val modifierForm = Modifier
        .fillMaxHeight(0.7f)
        .padding(20.dp)
    NavHost(
        navController = navController,
        startDestination = "Profile"
    ){
        composable("Profile") {
            val profileFormState = createProfileFormState(diarist)
            ProfileFormUi(
                profilePhoto = { /*TODO*/ },
                state = profileFormState,
                onEvent = {
                    onChangeProfile(it)
                }
            )
        }
        composable("Address") {
            val addressFormState = createAddressFormState(diarist.address)
            AddressFormUi(
                state = addressFormState,
                onChangeNumber = {
                    onChangeAddress(AddressFormEvent.NumberChanged(it))
                },
                onChangeCep = {
                    onChangeAddress(AddressFormEvent.CepChanged(it))
                },
                modifier =  modifierForm
            )
        }
    }
}
@Composable
fun RegisterScreen(
    diarist: Diarist,
    handlerAddressEvent: (AddressFormEvent) -> Unit,
    handlerProfileEvent : (ProfileFormEvent) -> Unit
) {
    val navController = rememberNavController()

    val modifierForm = Modifier
        .fillMaxHeight(0.7f)
        .padding(20.dp)

    var title by remember {
        mutableStateOf("T")
    }
    var description by remember {
        mutableStateOf("a")
    }
    var titleButton by remember {
        mutableStateOf("Próximo")
    }
    AuthContainer(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TitleSection(
            title = title,
            description = description
        )

        RegistrationNav(
            navController,
            onChangeAddress = {
                 handlerAddressEvent(it)
            },
            onChangeProfile = {
                handlerProfileEvent(it)
            },
            diarist = diarist
        )

        Button(onClick = {
            if (navController.currentDestination?.route == "Profile") {
                title = "Endereço"
                description = "Alou"
                navController.navigate("Address")
            } else {
                navController.navigate("Home")
            }
        }) {
            Text(text = titleButton)
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun TestRegister(){

        val TAG = "Rota de Registro"
        RegisterScreen(
            diarist = diaristTest,
            handlerAddressEvent = {
                Log.i(TAG, it.toString())
            },
            handlerProfileEvent = {
                Log.i(TAG, it.toString())
            }
        )

}