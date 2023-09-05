package br.senai.sp.jandira.limpeanapp.login

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.utils.Screen
import com.example.compose.LimpeanAppTheme
import com.example.compose.md_theme_dark_background
import com.example.compose.md_theme_dark_primary
import com.example.compose.md_theme_dark_secondary
import com.example.compose.md_theme_light_background
import com.example.compose.md_theme_light_error
import com.example.compose.md_theme_light_onBackground
import com.example.compose.md_theme_light_primary
import com.example.compose.md_theme_light_secondary

@Composable
fun LoginScreen() {
    Column (
        modifier = Modifier
            .background(md_theme_light_primary),
    ){
        Column(
            modifier = Modifier
                .padding(12.dp)
                .background(md_theme_light_error)
                .fillMaxSize()
        ) {
            Logo()
            Text(
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LimpeanAppTheme {
        LoginScreen()
    }
}

@Composable
fun Logo() {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}