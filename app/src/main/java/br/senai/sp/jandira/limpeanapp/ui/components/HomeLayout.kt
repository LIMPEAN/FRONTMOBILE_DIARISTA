package br.senai.sp.jandira.limpeanapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_light_primary

@Composable
fun HomeLayout(
    modifier : Modifier = Modifier,
    topBar : @Composable ()-> Unit,
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {paddingValues ->
        content(paddingValues)
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night Mode")
@Composable
fun HomeLayoutPreview() {
    LimpeanAppTheme {
        HomeLayout(topBar = { Text(text = "oi") }) {

        }
    }
}