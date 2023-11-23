package br.senai.sp.jandira.limpeanapp.core.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme

@Composable
fun HomeTopBar(
    modifier : Modifier = Modifier,
    titleSmall : String,
    titleLarge : String
){
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            )) {
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = titleSmall,
            color = MaterialTheme.colorScheme.primaryContainer,
            fontFamily = Poppins
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = titleLarge,
            color = MaterialTheme.colorScheme.primaryContainer,
            fontFamily = Poppins
        )
    }
}
@Preview(showBackground = true)
@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkMode")
@Composable
fun HomeAppBarPreview() {
    LimpeanAppTheme {
        HomeTopBar(
            titleSmall = "Bem Vindo",
            titleLarge = "Felipe"
        )
    }
}