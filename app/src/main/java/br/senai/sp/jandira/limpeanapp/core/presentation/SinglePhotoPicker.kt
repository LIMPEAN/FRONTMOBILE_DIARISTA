package br.senai.sp.jandira.limpeanapp.core.presentation

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.limpeanapp.R
import br.senai.sp.jandira.limpeanapp.core.data.remote.firebase.StorageUtil
import coil.compose.AsyncImage

@Composable
fun SinglePhotoPicker(
    onSaveUri: (Uri?) -> Unit,
){
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
            onSaveUri(it)
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
                    .size(140.dp)
                ,
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
                        uploadPick(uri!!, context)
                    }

                    // This will be called when the DisposableEffect is removed
                    onDispose { }
                }
            }
            Image(
                painterResource(id = R.drawable.add_a_photo),
                contentDescription = "",
                modifier = Modifier.size(height = 32.dp, width = 32.dp).clickable {

                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )

                }
            )
        }

    }

}
fun uploadPick(uri: Uri, context: Context){
    uri?.let{
        StorageUtil.uploadToStorage(uri = it, context = context, type = "image")
    }
}