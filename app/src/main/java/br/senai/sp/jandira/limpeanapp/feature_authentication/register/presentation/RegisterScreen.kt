package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import br.senai.sp.jandira.limpeanapp.core.presentation.components.TitleSection
import br.senai.sp.jandira.limpeanapp.feature_authentication.components.AuthContainer
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Address
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.domain.models.Gender
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form.ProfileForm
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form.ProfileFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.profile_form.rememberProfileForm
import com.example.compose.LimpeanAppTheme
import java.time.LocalDate

@Composable
fun RegisterScreen(
    state : Diarist,
    onEvent : (RegisterEvent) -> Unit
) {


    val profileFormState = rememberProfileForm(state)

    var repeatedPassword by remember {
        mutableStateOf("teste")
    }
    AuthContainer {
        TitleSection(
            title = stringResource(R.string.register_title),
            description = stringResource(R.string.register_description)
        )

        ProfileForm(
             
        )
    }

}




@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    LimpeanAppTheme {
        RegisterScreen(
            state = getListDiarists()[0],
            onEvent = {}
        )
    }

}
fun getListDiarists() : List<Diarist> {
    return listOf(
        Diarist(
            id = 1,
            name = "ana",
            cpf = "123456789089",
            phone ="11 91234-5678",
            email = "ana@gmail.com",
            password = "123456",
            dateOfBirth = LocalDate.of(2000,10,4),
            address = addressFake,
            photo = null,
            gender = Gender.FEMININO,
            biography = null
        ),Diarist(
            id = 2,
            name = "Giovani",
            cpf = "123456789089",
            phone ="11 91234-5678",
            email = "ana@gmail.com",
            password = "123456",
            dateOfBirth = LocalDate.of(2000,10,4),
            address = addressFake,
            photo = null,
            gender = Gender.MASCULINO,
            biography = null
        ),
    )
}

val addressFake = Address("","",
    "","", "", "", null)
