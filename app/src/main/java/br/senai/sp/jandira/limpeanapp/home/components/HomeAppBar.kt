package br.senai.sp.jandira.limpeanapp.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    LargeTopAppBar(
        modifier = Modifier
            .padding(20.dp)
        ,
        title = {
            Column() {
                Text(
                    text = "Bem vindo de volta"
                )
                Text(
                    text = "Leslie"
                )
            }

        }
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    LimpeanAppTheme {
        HomeAppBar()
    }
}