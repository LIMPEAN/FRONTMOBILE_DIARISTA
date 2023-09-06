package br.senai.sp.jandira.limpeanapp.registration.person

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.components.InputData
import br.senai.sp.jandira.limpeanapp.components.SectionTitle
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserEvent
import br.senai.sp.jandira.limpeanapp.registration.user.RegistrationUserState

@Composable
fun RegisterPersonScreen(
    userState: RegistrationUserState
) {

    val viewModel = viewModel<RegistrationPersonViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    val withoutError = ""


    Log.i("STATE-USER", userState.toString())


    Column (){
        Spacer(modifier = Modifier.height(60.dp))
        SectionTitle(
            title = stringResource(id = R.string.register),
            description = stringResource(id = R.string.register_personal)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ){

            InputData(
                label = stringResource(id = R.string.input_name),
                state = state.name,
                whenTyping = {
                    viewModel.onEvent(RegistrationPersonEvent.NameChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(26.dp))

        }

    }


}


@Preview( showSystemUi = true)
@Composable
fun RegisterPersonScreenPreview() {
    val navTest = rememberNavController()
    val testUser = RegistrationUserState()

    RegisterPersonScreen(
        userState = testUser
    )
}