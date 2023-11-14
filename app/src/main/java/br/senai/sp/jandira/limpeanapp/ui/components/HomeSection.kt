package br.senai.sp.jandira.limpeanapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.ui.theme.Poppins
import br.senai.sp.jandira.limpeanapp.ui.theme.poopins
import com.example.compose.LimpeanAppTheme
import org.w3c.dom.Text

@Composable
fun HomeSection(
    modifier : Modifier = Modifier.padding(24.dp),
    textAlign : TextAlign = TextAlign.Start,
    title : String,
    horizontalAlignment : Alignment.Horizontal = Alignment.Start,
    content : @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = title,
            textAlign = textAlign,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeSectionPrev() {
    LimpeanAppTheme {
        HomeSection(
            title = "Suas Faxinas"
        ){
            Text(text = "Card 1")

        }

    }
}
