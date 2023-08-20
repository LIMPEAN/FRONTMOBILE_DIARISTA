package br.senai.sp.jandira.limpeanapp.registration.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.components.Button
import br.senai.sp.jandira.limpeanapp.components.InputData
import br.senai.sp.jandira.limpeanapp.components.LabelError
import br.senai.sp.jandira.limpeanapp.components.SectionTitle
import com.example.compose.LimpeanAppTheme

@Composable
fun RegistrationUserScreen(
    navController: NavController,
    viewModel: RegistrationUserViewModel
) {

    val state = viewModel.state
    val context = LocalContext.current
    val withoutError = ""

    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect{ event ->
            when(event){
                is RegistrationUserViewModel.ValidationEvent.Success -> {
                    navController.navigate("register_personal")
                }
            }
        }
    }

    Column (){
        Spacer(modifier = Modifier.height(60.dp))
        SectionTitle(
            title = stringResource(id = R.string.register),
            description = stringResource(id = R.string.register_contact)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column (
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxWidth(),
        ){
           InputData(
               label = stringResource(R.string.input_email),
               myType = KeyboardType.Email,
               state = state.email,
               whenTyping = {
                    viewModel.onEvent(RegistrationUserEvent.EmailChanged(it))
               },
               isError = state.emailError != null
           )
           LabelError(
               text = state.emailError?: withoutError
           )
           InputData(
               label = stringResource(R.string.input_password),
               myType = KeyboardType.Password,
               state = state.password,
               whenTyping = {
                   viewModel.onEvent(RegistrationUserEvent.PasswordChanged(it))
               },
               visualTransformation = PasswordVisualTransformation(),
               isError = state.passwordError != null
           )
            LabelError(
                text = state.passwordError?: withoutError,
            )
           InputData(
               label = stringResource(R.string.input_confirm_password),
               myType = KeyboardType.Password,
               state = state.repeatedPassword,
               visualTransformation = PasswordVisualTransformation(),
               whenTyping = {
                  viewModel.onEvent(RegistrationUserEvent.RepeatedPasswordChanged(it))
               },
               isError = state.repeatedPasswordError != null
           )
            LabelError(
                text = state.repeatedPasswordError?: withoutError,
            )
           InputData(
               label = stringResource(R.string.input_phone),
               myType = KeyboardType.Phone,
               state = state.phone,
               whenTyping = {
                   viewModel.onEvent(RegistrationUserEvent.PhoneChanged(it))
               },
               isError = state.phoneError != null
           )
            LabelError(
                text = state.phoneError?: withoutError,
            )
           Spacer(modifier = Modifier.height(68.dp))
           Button(name = "Próximo", action = {
                viewModel.goToNextData()
           })
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.circle_decoration),
                contentDescription = "Circle Decoration",
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )

       }
        
    }
}


@Preview(showSystemUi = true)
@Composable
fun RegistrationUserScreenPreview() {
    val navControler = rememberNavController()
    val viewModel = viewModel<RegistrationUserViewModel>()
    LimpeanAppTheme {
        RegistrationUserScreen(
            navController = navControler,
            viewModel
        )
    }
}