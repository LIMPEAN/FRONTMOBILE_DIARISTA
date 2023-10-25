package br.senai.sp.jandira.limpeanapp.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = hiltViewModel()
){
    
    val state = viewModel.state
    Box {
        Column {
            Text(text = "Home Screen")
            Text(text = viewModel.session.toString())

        }
       
    }
}

