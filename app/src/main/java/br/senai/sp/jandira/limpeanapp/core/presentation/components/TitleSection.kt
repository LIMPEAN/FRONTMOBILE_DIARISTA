package br.senai.sp.jandira.limpeanapp.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondary

@Composable
fun TitleSection(
    title: String,
    description : String,
    modifier : Modifier = Modifier.fillMaxWidth(),
    horizontal: Alignment.Horizontal = Alignment.Start
) {
    Column (modifier = modifier,horizontalAlignment = horizontal){
        Text(text = title, color = md_theme_light_primary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = description)
    }
}

@Preview
@Composable
fun TitleSectionPreview() {
    LimpeanAppTheme {
        TitleSection(
            "Entrar",
            "Iniciar"
        )
    }
}