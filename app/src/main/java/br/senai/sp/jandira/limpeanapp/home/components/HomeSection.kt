package br.senai.sp.jandira.limpeanapp.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LimpeanAppTheme

@Composable
fun HomeSection(
    titleSection : String,
    content : @Composable () -> Unit
) {
    Column {
        Text(text = titleSection)
        Spacer(modifier = Modifier.height(10.dp))
        content()
    }
}

@Preview
@Composable
fun HomeSectionPrev() {
    LimpeanAppTheme {
        HomeSection(
            titleSection = "Suas Faxinas"
        ){
            Text(text = "Card 1")

        }

    }
}
