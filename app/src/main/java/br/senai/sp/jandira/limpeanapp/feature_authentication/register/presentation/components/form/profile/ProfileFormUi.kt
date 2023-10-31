package br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.components.form.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.core.presentation.components.PasswordField
import br.senai.sp.jandira.limpeanapp.core.presentation.components.text.NormalTextField
import br.senai.sp.jandira.limpeanapp.feature_authentication.register.presentation.RegisterEvent
import com.example.compose.LimpeanAppTheme




sealed class TextFieldVariant {
    object Password : TextFieldVariant()
    object Date : TextFieldVariant()
    object Normal : TextFieldVariant()
}


@Composable
fun ProfileFormUi(
    profilePhoto: @Composable () -> Unit,
    state: ProfileFormState,
    onEvent: (ProfileFormEvent) -> Unit,
    modifier: Modifier = Modifier.fillMaxHeight().padding(20.dp)
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        profilePhoto()
        NormalTextField(
            labelText = "Nome",
            value = state.name,
            onValueChange = { onEvent(ProfileFormEvent.NameChanged(it)) }
        )
        NormalTextField(
            labelText = "Cpf (apenas dígito)",
            value = state.cpf,
            onValueChange = { onEvent(ProfileFormEvent.CpfChanged(it)) },
            keyboardType = KeyboardType.Number
        )
        NormalTextField(
            labelText = "Email",
            value = state.email,
            onValueChange = { onEvent(ProfileFormEvent.EmailChanged(it)) },
            keyboardType = KeyboardType.Email
        )
        PasswordField(
            labelText = "Senha",
            state = state.password,
            onValueChange = { onEvent(ProfileFormEvent.PasswordChanged(it)) }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NormalTextField(
                modifier = Modifier.fillMaxWidth(0.3f),
                labelText = "DDD",
                value = state.ddd.toString(),
                onValueChange = { onEvent(ProfileFormEvent.DDDChanged(it)) },
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            NormalTextField(
                modifier = Modifier.fillMaxWidth(1f),
                labelText = "Telefone",
                value = state.phone,
                onValueChange = { onEvent(ProfileFormEvent.PhoneChanged(it)) },
                keyboardType = KeyboardType.Phone
            )
        }
    }
}



@Preview
@Composable
fun ProfileFormPreview() {
    LimpeanAppTheme {
        ProfileFormUi(
            modifier = Modifier.fillMaxSize(),
            state = ProfileFormState(),
            profilePhoto = {
                Text("Testando")
            },
            onEvent = {}
        )
    }
}