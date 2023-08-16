package br.senai.sp.jandira.limpeanapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.utils.poopins
import com.example.compose.LimpeanAppTheme
import com.example.compose.closeBlack

@Composable
fun SectionTitle(title : String, description : String) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text(color = closeBlack, text = title, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        Text(text = description, fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}

@Preview (showBackground = true)
@Composable
fun SectionTitlePreview() {
    LimpeanAppTheme {
        SectionTitle(title = "Teste", description = "Lorem impiidhskjbgfhbs")
    }

}