package br.senai.sp.jandira.limpeanapp.ui.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.data.UserRepository
import br.senai.sp.jandira.limpeanapp.data.UserTypesRepository
import br.senai.sp.jandira.limpeanapp.login.UserType
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary


@Composable
fun WelcomeScreen(
    onCreateAccount: (userType: UserType) -> Unit,
    onLogin: (userType: UserType) -> Unit,
) {



    val userTypes = UserTypesRepository.getAll()
    var selected by remember {
        mutableStateOf(userTypes[0])
    }

    Column(
        modifier = Modifier
            .background(md_theme_light_primary),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
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
                        selected = selected == type,
                        onClick = {
                            selected = type
                        }
                    )
                    Text(
                        text = type.portugueseName, // Provide the label for the user type
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
            Row {
                Button(
                    onClick = {
                        onCreateAccount(selected)
                    }
                ) {
                    Text(text = "Cadastro")
                }
                Button(
                    onClick = {
                        onLogin(selected)
                    }
                ) {
                    Text(text = "Login")
                }
            }


        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {

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

