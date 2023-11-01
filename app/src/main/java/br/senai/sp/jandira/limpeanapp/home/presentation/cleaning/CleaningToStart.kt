package br.senai.sp.jandira.limpeanapp.home.presentation.cleaning

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.home.presentation.cleaning.components.CleaningSchedules
import br.senai.sp.jandira.limpeanapp.home.data.repository.cleanings
import br.senai.sp.jandira.limpeanapp.home.components.HomeContent
import br.senai.sp.jandira.limpeanapp.home.components.HomeSection
import br.senai.sp.jandira.limpeanapp.home.components.HomeTopBar
import br.senai.sp.jandira.limpeanapp.home.components.HomeLayout
import com.example.compose.LimpeanAppTheme

@Preview(showSystemUi = true)
@Composable
fun CleaningToStart() {
    HomeLayout(
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                title = "Bem Vindo",
                description = "Felipe"
            )
        }
    ) {
        HomeContent(
            paddingValues = it
        ) {
            HomeSection(title = "Suas faxinas") {
                CleaningSchedules(cleanings = cleanings)
            }
        }
    }
}

@Preview
@Composable
fun CleaningToStartPreview() {
    LimpeanAppTheme {
        CleaningToStart()
    }
}