package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.components.AuthContainer
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.core.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.createAddressFormState

import com.example.compose.LimpeanAppTheme
import java.time.LocalDate




@Composable
fun RegisterScreen(
    titleSection: @Composable ()-> Unit,
    onButtonClick: () -> Unit,
    titleButton: String,
    form: @Composable ()-> Unit,
) {


    val scrollState = rememberScrollState()

    AuthContainer(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .scrollable(
                scrollState,
                Orientation.Vertical
            )
    ) {
        titleSection()

        Box(modifier = Modifier.height(IntrinsicSize.Max)){
            form()
        }


        Button(onClick = { onButtonClick() } ){
            Text(text = titleButton)
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

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {

    LimpeanAppTheme {
        RegisterScreen(
            titleSection = {
                TitleSection(
                    horizontal = Alignment.CenterHorizontally,
                    title = stringResource(R.string.register_title),
                    description = stringResource(R.string.register_description)
                )
            },
            onButtonClick = {},
          
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
