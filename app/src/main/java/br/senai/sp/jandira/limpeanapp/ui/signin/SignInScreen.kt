package br.senai.sp.jandira.limpeanapp.ui.signin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.domain.Gender
import br.senai.sp.jandira.limpeanapp.domain.Person
import br.senai.sp.jandira.limpeanapp.ui.signin.components.PersonForm
import br.senai.sp.jandira.limpeanapp.ui.welcome.Logo
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_onPrimaryContainer
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    title: String,
    content: @Composable () -> Unit,
    onClickButton: () -> Unit,
    nameActionButton : String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(12.dp),
                title = { Text(text = title) },
                colors = TopAppBarDefaults.mediumTopAppBarColors()// Customize the top app bar color
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content() // PersonForm(), UserForm(), AdreesForm(), HomeForm()

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = onClickButton) {
                    Text(nameActionButton)
                }
            }
        }
    )
}

@Preview
@Composable
fun SignInPersonScreenPreview() {
    var person = Person(
        "Felipe",
        Date(),
        Gender.values()[0],
        "",
        "")
    LimpeanAppTheme {
        SignInScreen(
            title = "Dados Pessoais",
            content = { PersonForm(person = person, {}) },
            nameActionButton = "Próximo",
            onClickButton = {}
        )
    }
}