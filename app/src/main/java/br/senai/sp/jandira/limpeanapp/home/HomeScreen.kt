package br.senai.sp.jandira.limpeanapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.data.repository.TokenRepositoryImpl
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.di.LoginModule
import br.senai.sp.jandira.limpeanapp.feature_authentication.login.domain.TokenRepository

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = hiltViewModel()
){
    
    val state = viewModel.state
    Box {
        Column {
            Text(text = "Home Screen")
            Text(text = state.token!!)
            Button(onClick = {viewModel.getToken()}) {
                Text(text = "Get Token")
            }
        }
       
    }
}

@Preview
@Composable
fun HomePrev() {
    val context = LocalContext.current
    HomeScreen(
        viewModel = HomeViewModel(tokenManager = TokenRepositoryImpl(
            LoginModule.providePreferencesDataStore(context)
        )
        )
    )
}