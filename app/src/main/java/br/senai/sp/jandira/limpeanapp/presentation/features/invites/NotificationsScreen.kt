package br.senai.sp.jandira.limpeanapp.presentation.features.invites

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.presentation.home.components.HomeTopBar
import com.example.compose.LimpeanAppTheme
import com.example.compose.seed


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationsScreen() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
                 HomeTopBar(
                     modifier = Modifier
                                .padding(horizontal = 24.dp),
                     titleSmall = "Visualize suas",
                     titleLarge = "Notificações")
        },

        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = seed), verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White),

                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {


//    ---------------------     Caso não tenha nenhuma notificação ainda -----------------------

//                        Text(
//                            text = stringResource(id = R.string.no_notifications),
//                            modifier = Modifier.width(450.dp),
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 26.sp,
//                            fontFamily = customFontFamily
//                        )
//
//                        Image(
//                            painter = painterResource(id = R.drawable.push_notifications),
//                            contentDescription = "",
//                            modifier = Modifier
//                                .size(256.dp)
//                                .align(
//                                    Alignment.CenterHorizontally
//                                )
//                        )

                        Card(modifier = Modifier.fillMaxWidth(),
                             colors = CardDefaults.cardColors(Color.White),
                             shape = RoundedCornerShape(0.dp),
                             border = BorderStroke(1.dp, color = Color.Gray)
                            ){
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Image(painter = painterResource(id = R.drawable.logo_google), contentDescription = "teste" )
                                Text(text = "testando 1 2 3")
                                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription ="teste")
                            }
                        }


                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationPreview() {
    LimpeanAppTheme {
        NotificationsScreen()
    }

}