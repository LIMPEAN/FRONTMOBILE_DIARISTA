package br.senai.sp.jandira.limpeanapp.ui.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.data.UserTypesRepository
import br.senai.sp.jandira.limpeanapp.domain.UserType
import br.senai.sp.jandira.limpeanapp.ui.welcome.components.SectionButton
import br.senai.sp.jandira.limpeanapp.ui.welcome.components.SelectUserType
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary


@Composable
fun WelcomeScreen(
    onCreateAccount: (userType: UserType) -> Unit,
    onLogin: (userType: UserType) -> Unit,
) {
    val userTypes = UserTypesRepository.getAll()
    var selectedUserType by remember {
        mutableStateOf(userTypes[0])
    }

    val heightModifiers = 28.dp


    Column(
        modifier = Modifier
            .background(md_theme_light_primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 32.dp),
        ) {
            Logo()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightModifiers)
            )
            Text(
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.cleaning_service_cuate_1),
                contentDescription = "Cleaning Service Home",
                Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightModifiers)
            )

            SelectUserType(
                userTypes = userTypes,
                selectedUserType = selectedUserType,
                onSelectedChange = { selectedUserType = it }
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionButton(name = "Login") {
                    onLogin(selectedUserType)
                }
                Spacer(modifier = Modifier.height(12.dp))
                SectionButton(name = "Cadastro") {
                    onCreateAccount(selectedUserType)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {

    LimpeanAppTheme {
        WelcomeScreen(
            onCreateAccount = {
                Log.i("onCreated", it.toString())
            },
            onLogin = { Log.i("onLogin", it.toString())})
    }
}

@Composable
fun Logo() {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}
