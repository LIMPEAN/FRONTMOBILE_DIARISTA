package br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun TitleSection(
    title: String,
    description : String,
    modifier : Modifier = Modifier.fillMaxWidth(),
    horizontal: Alignment.Horizontal = Alignment.Start
) {
    Column (modifier = modifier,horizontalAlignment = horizontal){
        Text(text = title, color = MaterialTheme.colorScheme.primary, fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins, style = MaterialTheme.typography.headlineMedium)
        Text(text = description, color = MaterialTheme.colorScheme.outline, fontFamily = Poppins, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}



@Preview
@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark mode")

@Composable
fun TitleSectionPreview() {
    LimpeanAppTheme {
        TitleSection(
            "Entrar",
            "Iniciar"
        )
    }
}