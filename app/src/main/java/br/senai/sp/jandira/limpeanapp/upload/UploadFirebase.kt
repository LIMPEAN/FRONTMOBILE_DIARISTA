package br.senai.sp.jandira.limpeanapp.upload

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.upload.ui.theme.LimpeanAppTheme
import coil.compose.AsyncImage

class UploadFirebase : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimpeanAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SinglePhotoPicker()                }
            }
        }
    }
}

@Composable
fun SinglePhotoPicker(){
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    val context = LocalContext.current


    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ){

        Box(
            contentAlignment = Alignment.BottomEnd

        ) {

            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        singlePhotoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                shape = CircleShape,
            ) {
                AsyncImage(
                    model = uri ?: R.drawable.profile,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                DisposableEffect(uri) {
                    if (uri != null) {
                        // Call your UploadPick function here when uri is updated
                        UploadPick(uri!!, context)
                    }

                    // This will be called when the DisposableEffect is removed
                    onDispose { }
                }
            }
            Image(
                painterResource(id = R.drawable.add_a_photo),
                contentDescription = "",
                modifier = Modifier.size(height = 32.dp, width = 32.dp)
            )
        }

    }

}

    fun UploadPick(uri: Uri, context: Context){
        uri?.let{
            StorageUtil.uploadToStorage(uri=it, context=context, type="image")
        }
    }


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadFirebasePreview() {
    SinglePhotoPicker()
}
