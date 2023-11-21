package br.senai.sp.jandira.limpeanapp.core.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.LimpeanAppTheme

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
        HomeLayout(
            topBar = { Text(text = "oi") },
        ) {

        }
    }
}