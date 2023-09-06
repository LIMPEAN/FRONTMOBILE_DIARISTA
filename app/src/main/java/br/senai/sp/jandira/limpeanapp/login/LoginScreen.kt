package br.senai.sp.jandira.limpeanapp.login

import android.annotation.SuppressLint
import android.media.tv.TvContract.Channels.Logo
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.diarists.domain.Diarist
import br.senai.sp.jandira.limpeanapp.domain.Address
import br.senai.sp.jandira.limpeanapp.domain.Gender
import br.senai.sp.jandira.limpeanapp.domain.Person
import br.senai.sp.jandira.limpeanapp.domain.Phone
import br.senai.sp.jandira.limpeanapp.domain.User
import br.senai.sp.jandira.limpeanapp.registration.person.RegistrationPersonViewModel
import br.senai.sp.jandira.limpeanapp.utils.Screen
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_background
import com.example.compose.md_theme_dark_primary
import com.example.compose.md_theme_dark_secondary
import com.example.compose.md_theme_light_background
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_onBackground
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondary
import kotlinx.coroutines.selects.select
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    onLogin : ()-> Unit,
    onCreateAccount: ()-> Unit
) {

    val viewModel = viewModel<LoginViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    val userTypes = viewModel.state.userTypes


    var selectedUserType by remember { mutableStateOf(state.typeUser) }

    Column(
        modifier = Modifier
            .background(md_theme_light_primary),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .background(md_theme_light_error)
                .fillMaxSize()
        ) {
            Logo()
            Text(
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (type in userTypes) {
                    RadioButton(
                        selected = state.typeUser == type,
                        onClick = {
                            viewModel.onEvent(LoginEvent.selectedChange(type))
                        }
                    )
                    Text(
                        text = type.portugueseName, // Provide the label for the user type
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
            Button(onClick = onCreateAccount) {
                Text(text = "Cadastrar")
            }
            Button(onClick = onLogin) {
                Text(text = "Login")
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {

    LimpeanAppTheme {
       LoginScreen(onLogin = { /*TODO*/ }) {
           
       }
    }
}

@Composable
fun Logo() {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}




