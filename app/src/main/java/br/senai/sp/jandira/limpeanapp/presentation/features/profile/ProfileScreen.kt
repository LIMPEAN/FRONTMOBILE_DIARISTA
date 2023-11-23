package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Address
import br.senai.sp.jandira.limpeanapp.core.presentation.SinglePhotoPicker
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.AddressFormUi
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.address.createAddressFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormState
import br.senai.sp.jandira.limpeanapp.feature_authentication.presentation.register.components.form.profile.ProfileFormUi
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme

@Composable
fun ProfileScreen() {
    LimpeanAppTheme {
        SettingsScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    var showDialog by remember {
        mutableStateOf(false)
    }
    var showProfile by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderText()
        ProfileCardUI(
            painterResource(id = R.drawable.man),
            email = "Felipe@gmail.com",
            onSeeProfile = {showProfile = true}
        )
        GeneralOptionsUI(
            onTheme = { showDialog = true},
            onLogout = {},
            onNotImplemented = {}
        )
        SupportOptionsUI()
    }
    if(showDialog){
          ThemeOptionsDialog(
              onDismissRequest = {showDialog = false},
              onApply = {},
              onCancel = {showDialog = false}
          )
    }
    if(showProfile){
        Dialog(
            onDismissRequest = {showProfile = false},
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Text(text = "Meu Perfil")
                    ProfileFormUi(profilePhoto = { SinglePhotoPicker(onSaveUri = {}, onSaveUrl = {}) }, state = ProfileFormState(), onEvent = {})
                    Text(text = "Endereço")
                    AddressFormUi(state = createAddressFormState(Address()), onEvent = {}, modifier = Modifier)
                }

            }

        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SettingsScreenPreview() {
    LimpeanAppTheme {
        SettingsScreen()
    }
}

@Composable
fun HeaderText() {
    Text(
        text = "Configurações",
        fontFamily = Poppins,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
}

@Composable
fun ProfileCardUI(
    profileImage : Painter,
    email : String,
    onSeeProfile : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Text(
                    text = "Configure seu Perfil",
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = email,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )


                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = {onSeeProfile()},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Ver",
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                painter = profileImage,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun GeneralOptionsUI(
    onTheme : () -> Unit,
    onLogout: () ->Unit,
    onNotImplemented : () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Gerais",
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            icon = Icons.Default.Assistant,
            mainText = "Avaliações",
            subText = "Veja o histórico.",
            onClick = { onNotImplemented()}
        )
        GeneralSettingItem(
            icon = Icons.Default.Notifications,
            mainText = "Notificações",
            subText = "Configure suas preferências",
            onClick = { onLogout() }
        )
        GeneralSettingItem(
            icon = Icons.Default.DashboardCustomize,
            mainText = "Temas",
            subText = "Personalize as cores",
            onClick = { onTheme() }
        )
        GeneralSettingItem(
            icon = Icons.Default.Logout,
            mainText = "Sair",
            showArrow = false
        ){onNotImplemented()}

    }
}


@Composable
fun GeneralSettingItem(
    icon: ImageVector,
    mainText: String,
    subText: String? = null,
    showArrow : Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    subText?.let {
                        Text(
                            text = it,
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.offset(y = (-4).dp)
                        )
                    }

                }
            }
            if(showArrow){
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }



        }
    }
}



@Composable
fun SupportOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Suporte",
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = Icons.Default.Phone,
            mainText = "Contato",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.PrivacyTip,
            mainText = "Política de Privacidade",
            onClick = {}
        )
        SupportItem(
            icon = Icons.Default.Info,
            mainText = "Sobre nós",
            showArrow = false,
            onClick = {}
        )
    }
}


@Composable
fun SupportItem(
    icon: ImageVector,
    mainText: String,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            if(showArrow){
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }


        }
    }
}


data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)

@Preview
@Composable
fun ThemeOptionsDialog(
    onDismissRequest : () -> Unit ={},
    onCancel : () -> Unit ={},
    onApply : ()->Unit ={},
){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = onApply,
            ) {
                Text(
                    text = "Aplicar",
                    fontFamily = Poppins,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        dismissButton = {
             Button(
                 onClick = onCancel,
                 colors = ButtonDefaults.buttonColors(
                     contentColor = MaterialTheme.colorScheme.onSecondary,
                     containerColor = MaterialTheme.colorScheme.secondary
                 )
             ) {
                 Text(
                     text = "Cancelar",
                     fontFamily = Poppins,
                     style = MaterialTheme.typography.labelSmall
                 )
             }
        },
        icon = {
            Icon(
                imageVector = Icons.Default.DashboardCustomize,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        title = {
            Text(
                fontFamily = Poppins,
                text = "Definir Tema",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        text = {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThemeOptions()
            }
        },
    )
}
@Composable
fun ThemeOptions() {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = true,
                text = "Automático (Padrão)"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Modo escuro"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Modo claro"
            ),
        )
    }

    radioButtons.forEachIndexed { index, info ->
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.text == info.text
                        )
                    }
                }
        ) {
            RadioButton(
                modifier = Modifier.size(48.dp),
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.text == info.text
                        )
                    }
                }
            )
            Text(
                text = info.text,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemeDialogPreview() {
    LimpeanAppTheme {
        ThemeOptionsDialog()
    }
    
}