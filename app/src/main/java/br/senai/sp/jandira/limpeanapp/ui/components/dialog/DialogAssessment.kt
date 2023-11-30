package br.senai.sp.jandira.limpeanapp.ui.components.dialog

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.domain.models.Client
import br.senai.sp.jandira.limpeanapp.core.domain.models.Diarist
import br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components.StarView
import br.senai.sp.jandira.limpeanapp.presentation.ui.theme.Poppins
import com.example.compose.LimpeanAppTheme
//import br.senai.sp.jandira.limpeanapp.ui.features.cleaning.components.StarView
import com.example.compose.seed
import org.checkerframework.checker.units.qual.UnitsMultiple


@Preview
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AssentmentDialogPreview() {
    LimpeanAppTheme {
        val context = LocalContext.current
        var isShowDialog by remember {
            mutableStateOf(true)
        }
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { isShowDialog = true}) {
                Text(text = "Show Dialog")
            }
        }
        if (isShowDialog){
            AssentmentDialog(
                name = "Felipe",
                profileImage = painterResource(id = R.drawable.man),
                onAssentment = {
                    Toast.makeText(context, "Avaliar", Toast.LENGTH_LONG).show()
                },
                onCancel = {
                    isShowDialog = false
                },
                error = "Requer estrela selecionada!"
            )
        }
    }
}
data class AssentmentState(
    val stars : Double = 0.0,
    val comment : String = "",
    val error: String? = null,
    val client : Client = Client(),
    val diarist : Diarist = Diarist()
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssentmentDialog(
    name: String,
    profileImage: Painter,
    onAssentment: (AssentmentState) -> Unit,
    onCancel: () -> Unit,
    error : String? = null
) {
    val customFontFamily = FontFamily(
        Font(R.font.poppins_regular)
    )
    var rating by remember {
        mutableDoubleStateOf(0.0)
    }
    var comment by remember {
        mutableStateOf("")
    }



    Dialog(
        onDismissRequest = onCancel,
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            val color = MaterialTheme.colorScheme.onBackground
            val primary = MaterialTheme.colorScheme.primary
            Column(
                Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle()) {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = customFontFamily,
                                    color = color
                                )
                            ) {
                                append("Como foi o suporte de ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    color = primary,
                                    fontSize = 16.sp,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append(name)
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = color,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = customFontFamily
                                )
                            ) {
                                append("?")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = profileImage,
                    contentDescription = "",
                    modifier = androidx.compose.ui.Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = "Ajude a melhorar a plataforma avaliando como foi sua relação com o cliente durante todo o processo.",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = Poppins,
                    textAlign = TextAlign.Center
                )
                StarView(
                    modifier = Modifier.fillMaxWidth(),
                    starSize = 36.dp,
                    rating = rating,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    }
                )
                error?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        fontFamily = Poppins,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CommentBox(
                    content = comment,
                    onContentChange = {comment = it}
                )
                AssentmentActions(
                    onAssentment = { onAssentment(
                        AssentmentState(
                            stars = rating,
                            comment = comment
                        )
                    ) },
                    onCancel = onCancel
                )
            }
        }
    }
}


@Composable
fun AssentmentActions(
    onAssentment: () -> Unit,
    onCancel: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onAssentment,
            modifier = Modifier
                .width(110.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            shape = RoundedCornerShape(size = 12.dp),
        ) {
            Text(
                text = stringResource(R.string.avaliar),
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Button(
            onClick = onCancel,
            modifier = Modifier
                .width(110.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            shape = RoundedCornerShape(size = 12.dp),
        ) {
            Text(
                text = "Cancelar",
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Composable
fun CommentBox(
    content : String,
    onContentChange : (String) -> Unit
) {
    OutlinedTextField(
        value = content, onValueChange = onContentChange,
        modifier = Modifier
            .height(120.dp),
        textStyle = MaterialTheme.typography.bodySmall,
        placeholder = {
            Text(
                text = "Escreva aqui como foi sua experiência e ajuda prestada. (opcional)",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = Poppins,
                style = MaterialTheme.typography.bodySmall
            )

        },
        maxLines = 5,
    )
}