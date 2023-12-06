package br.senai.sp.jandira.limpeanapp.presentation.features.profile

import android.content.Context
import android.net.Uri

import android.widget.Toast

import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID


//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun YourProfile(
//    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>(),
//) {
//    viewModel.carregarDiarista()
//
//
//    val resultado = viewModel.resultado
//    val api: DiaristApi
//
//    var isDialogVisible by remember { mutableStateOf(false) }
//
//
//    val totalStars = resultado?.assessment?.sumBy { it.stars } ?: 0
//    val averageStars = if (totalStars != 0 && resultado?.assessment?.isNotEmpty() == true) {
//        totalStars.toDouble() / resultado.assessment.size
//    } else {
//        0.0
//    }
//
//
//    //fonte
//    val customFontFamily = FontFamily(
//        Font(R.font.poppins_regular)
//    )
//
//
//    //foto config
//    var uri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val singlePhotoPicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = {
//            uri = it
//        }
//    )
//
//    val context = LocalContext.current
//
//    //foto config
//
//    resultado?.let {
//        Scaffold(
//            modifier = Modifier
//                .fillMaxSize(),
//            bottomBar = {
//
//            },
//
//            content = {
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .absoluteOffset(y = 20.dp)
//                        .zIndex(Float.POSITIVE_INFINITY),
//                    horizontalArrangement = Arrangement.Center,
//                ) {
//                    //foto aqui
//                    Column(
//                    ) {
//
//                        Box(
//                            contentAlignment = Alignment.BottomEnd
//
//                        ) {
//
//                            Card(
//                                modifier = Modifier
//                                    .width(100.dp)
//                                    .height(100.dp)
//                                    .clickable {
//                                        singlePhotoPicker.launch(
//                                            PickVisualMediaRequest(
//                                                ActivityResultContracts.PickVisualMedia.ImageOnly
//                                            )
//                                        )
//                                    },
//                                shape = CircleShape,
//                            ) {
//                                AsyncImage(
//                                    model = uri ?: resultado.photoProfile,
//                                    contentDescription = null,
//                                    modifier = Modifier.fillMaxSize(),
//                                    contentScale = ContentScale.Crop
//                                )
//
//                                DisposableEffect(uri) {
//                                    if (uri != null) {
//                                        // Call your UploadPick function here when uri is updated
//                                        UploadPick(uri!!, context)
//                                    }
//
//                                    // This will be called when the DisposableEffect is removed
//                                    onDispose { }
//                                }
//                            }
//                            Image(
//                                painterResource(id = R.drawable.add_a_photo),
//                                contentDescription = "",
//                                modifier = Modifier.size(height = 32.dp, width = 32.dp)
//                            )
//                        }
//
//                    }
//                    //foto aqui
//
//                }
//
//                Column(
//                    Modifier
//                        .fillMaxSize()
//                        .background(color = seed), verticalArrangement = Arrangement.Bottom
//                ) {
//                    Spacer(modifier = Modifier.height(100.dp))
//                    Card(
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        colors = CardDefaults.cardColors(Color.White),
//
//                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
//                    ) {
//                        Spacer(modifier = Modifier.height(40.dp))
//                        Column {
//                            LazyColumn(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                items(1) {
//
//                                    Text(
//                                        text = resultado.name,
//                                        fontSize = 28.sp,
//                                        fontFamily = customFontFamily,
//                                        lineHeight = 20.sp,
//                                        fontWeight = FontWeight(600),
//                                        color = Color(0xFF19161D),
//                                        letterSpacing = 0.2.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    if (averageStars != 0.0) {
//                                        StarView(
//                                            rating = (averageStars)
//                                        )
//                                    } else {
//                                        Text(text = "Novo", fontFamily = customFontFamily)
//                                        StarView(
//                                            rating = (5.0)
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(30.dp))
//
//                                    Text(
//                                        text = "Biografia:",
//                                        modifier = Modifier.fillMaxWidth(),
//                                        fontFamily = customFontFamily,
//                                        fontSize = 18.sp,
//                                        lineHeight = 20.sp,
//                                        fontWeight = FontWeight(600),
//                                        color = Color.Black,
//                                        textAlign = TextAlign.Left,
//                                        letterSpacing = 0.2.sp
//                                    )
//                                    Spacer(modifier = Modifier.height(8.dp))
//                                    LazyColumn(modifier = Modifier.height(80.dp)) {
//                                        item {
//                                            Column(modifier = Modifier.fillMaxSize()) {
//
//                                                Text(
//                                                    text = resultado.biography,
//                                                    modifier = Modifier
//                                                        .fillMaxWidth()
//                                                        .wrapContentHeight(),
//                                                    fontSize = 16.sp,
//                                                    fontFamily = customFontFamily,
//                                                    lineHeight = 20.sp,
//                                                    fontWeight = FontWeight.Light,
////                                                               color = Color(25, 22, 29),
//                                                    color = md_theme_dark_outlineVariant,
//                                                    textAlign = TextAlign.Justify,
//                                                    letterSpacing = 0.2.sp
//                                                )
//                                            }
//                                        }
//                                    }
//
//                                    Spacer(modifier = Modifier.height(10.dp))
//
//                                    Text(
//                                        text = "Dados pessoais:",
//                                        modifier = Modifier.fillMaxWidth(),
//                                        fontSize = 18.sp,
//                                        fontFamily = customFontFamily,
//                                        lineHeight = 20.sp,
//                                        fontWeight = FontWeight(600),
//                                        color = Color.Black,
//                                        textAlign = TextAlign.Left,
//                                        letterSpacing = 0.2.sp
//                                    )
//                                    Spacer(modifier = Modifier.height(10.dp))
//
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            text = stringResource(id = R.string.gender).toUpperCase(),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight(600),
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Left,
//                                            letterSpacing = 0.2.sp
//                                        )
//
//                                        Text(
//                                            text = resultado.gender,
//                                            modifier = Modifier
//                                                .padding(start = 5.dp),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight.Light,
//                                            color = md_theme_dark_outlineVariant,
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(5.dp))
//
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            text = stringResource(id = R.string.locality).toUpperCase(),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight(600),
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Left,
//                                            letterSpacing = 0.2.sp
//                                        )
//
//                                        Text(
//                                            text = buildAnnotatedString {
//                                                resultado?.address?.firstOrNull()
//                                                    ?.let { addressDto ->
//                                                        append("${addressDto.city}, ${addressDto.state}")
//                                                    }
//                                            },
//                                            modifier = Modifier
//                                                .padding(start = 5.dp),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight(100),
//                                            color = md_theme_dark_outlineVariant,
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(5.dp))
//
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                    ) {
//                                        Text(
//                                            text = stringResource(id = R.string.phone).toUpperCase(),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight(600),
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Left,
//                                            letterSpacing = 0.2.sp
//                                        )
//
//                                        Text(
//                                            text = buildAnnotatedString {
//                                                resultado?.phone?.firstOrNull()?.let { phoneDto ->
//                                                    append("${phoneDto.ddd} ${phoneDto.numberPhone}")
//                                                }
//                                            },
//                                            modifier = Modifier
//                                                .padding(start = 5.dp),
//                                            fontSize = 14.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight.Light,
//                                            color = md_theme_dark_outlineVariant,
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(20.dp))
//
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth(),
//                                        horizontalArrangement = Arrangement.SpaceBetween
//                                    ) {
//                                        var isFormVisible by remember { mutableStateOf(false) }
//                                        Button(
//                                            modifier = Modifier
//                                                .weight(1f)
//                                                .height(45.dp),
//                                            colors = ButtonDefaults.buttonColors(containerColor = seed),
//                                            shape = RoundedCornerShape(size = 8.dp),
//                                            onClick = {
//                                                //atualizar precisamos endereço completo, todos os dados pessoais
//                                                // Chame a função ProfileFormUi dentro do onClick
//                                                isFormVisible = true
//                                            }
//                                        ) {
//                                            Text(
//                                                text = stringResource(id = R.string.edit_profile_diarist),
//                                                fontWeight = FontWeight.Bold,
//                                                fontSize = 14.sp,
//                                                fontFamily = customFontFamily,
//                                            )
//                                        }
//
////                                        // Verifique se o formulário deve ser exibido e, em caso afirmativo, chame a função dentro de um composable
////                                        if (isFormVisible) {
////                                            ProfileFormDialog(
////                                                onDismiss = {
////                                                    // Defina isFormVisible como false quando o formulário for fechado isFormVisible = false
////                                                }
////                                            )
////                                        }
//                                            Spacer(modifier = Modifier.width(8.dp))
//
//                                            Button(
//                                                modifier = Modifier
//                                                    .weight(1f)
//                                                    .height(45.dp),
//                                                colors = ButtonDefaults.buttonColors(containerColor = seed),
//                                                shape = RoundedCornerShape(size = 8.dp),
//                                                onClick = ({
//                                                    //chamando dialog alert para confirmaçao
//                                                    isDialogVisible = true
//
//                                                })
//                                            ) {
//                                                Text(
//                                                    text = stringResource(id = R.string.delete_account),
//                                                    fontWeight = FontWeight.Bold,
//                                                    fontSize = 14.sp,
//                                                    fontFamily = customFontFamily,
//                                                )
//                                            }
//
//                                            if (isDialogVisible) {
//                                                AlertDialog(
//                                                    onDismissRequest = {
//                                                        isDialogVisible = false
//                                                    },
//                                                    onConfirmation = {
//                                                        viewModel.viewModelScope.launch {
//                                                            try {
//                                                                viewModel.apagarDiarista()
//                                                            } catch (e: Exception) {
//                                                                Log.e(
//                                                                    "Chamada ApagarDiarista",
//                                                                    "Erro: ${e.message}"
//                                                                )
//                                                            }
//                                                        }
//                                                    },
//                                                    dialogTitle = "Deseja excluir sua conta?",
//                                                    dialogText = "Todos os demais cadastros vinculados a esta conta também serão removidos.",
//                                                    icon = Icons.Default.Info
//                                                )
//                                            }
//                                        }
//
//
//                                        Spacer(modifier = Modifier.height(30.dp))
//
//                                        Text(
//                                            text = stringResource(id = R.string.others),
//                                            modifier = Modifier.fillMaxWidth(),
//                                            fontSize = 12.sp,
//                                            fontFamily = customFontFamily,
//                                            lineHeight = 20.sp,
//                                            fontWeight = FontWeight(600),
//                                            color = Color.Black,
//                                            textAlign = TextAlign.Left,
//                                            letterSpacing = 0.2.sp
//                                        )
//
//                                        Spacer(modifier = Modifier.height(10.dp))
//
//                                        Row(
//                                            modifier = Modifier.fillMaxWidth(),
//                                            horizontalArrangement = Arrangement.Start,
//                                            verticalAlignment = Alignment.CenterVertically
//                                        ) {
//                                            Image(
//                                                painter = painterResource(id = R.drawable.comments_disabled),
//                                                contentDescription = "",
//                                                modifier = Modifier
//                                                    .size(22.dp)
//                                            )
//                                            Spacer(modifier = Modifier.width(5.dp))
//                                            Text(
//                                                text = stringResource(id = R.string.talk_to_support),
//                                                fontSize = 12.sp,
//                                                fontFamily = customFontFamily,
//                                                lineHeight = 20.sp,
//                                                fontWeight = FontWeight(600),
//                                                color = Color.Black,
//                                                textAlign = TextAlign.Left,
//                                                letterSpacing = 0.2.sp
//                                            )
//
//                                        }
//                                        Row(
//                                            modifier = Modifier.fillMaxWidth(),
//                                            horizontalArrangement = Arrangement.Start,
//                                            verticalAlignment = Alignment.CenterVertically
//                                        )
//                                        {
//                                            Image(
//                                                painter = painterResource(id = R.drawable.admin_panel_settings),
//                                                contentDescription = "",
//                                                modifier = Modifier
//                                                    .size(22.dp)
//                                            )
//                                            Spacer(modifier = Modifier.width(5.dp))
//                                            Text(
//                                                text = stringResource(id = R.string.privacy_policy),
//                                                fontSize = 12.sp,
//                                                fontFamily = customFontFamily,
//                                                lineHeight = 20.sp,
//                                                fontWeight = FontWeight(600),
//                                                color = Color.Black,
//                                                textAlign = TextAlign.Left,
//                                                letterSpacing = 0.2.sp
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                )
//            }
//    }

//    fun UploadPick(uri: Uri, context: Context) {
//        uri?.let {
//            StorageUtil.uploadToStorage(uri = it, context = context, type = "image")
//        }
//    }

    class StorageUtil {
        companion object {

            fun uploadToStorage(uri: Uri, context: Context, type: String) {
                val storage = Firebase.storage

                // Create a storage reference from our app
                var storageRef = storage.reference

                val unique_image_name = UUID.randomUUID()
                var spaceRef: StorageReference

                if (type == "image") {
                    spaceRef = storageRef.child("imagen s/$unique_image_name.jpg")
                } else {
                    spaceRef = storageRef.child("videos/$unique_image_name.mp4")
                }

                val byteArray: ByteArray? = context.contentResolver
                    .openInputStream(uri)
                    ?.use { it.readBytes() }

                byteArray?.let {

                    var uploadTask = spaceRef.putBytes(byteArray)
                    uploadTask.addOnFailureListener {
                        Toast.makeText(
                            context,
                            "upload failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Handle unsuccessful uploads
                    }.addOnSuccessListener { taskSnapshot ->
                        // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                        // ...
                        Toast.makeText(
                            context,
                            "upload successed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }

        }
    }

//    @Composable
//    fun ProfileFormDialog(onDismiss: () -> Unit) {
//        Dialog(
//            onDismissRequest = {
//                // Chame onDismiss ao fechar o diálogo
//                onDismiss()
//            }
//        ) {
//            // Coloque o conteúdo do formulário aqui
//            ProfileFormUi(
//                profilePhoto = { SinglePhotoPicker(onSaveUri = {}) },
//                state = ProfileFormState(),
//                onEvent = { event ->
//
//                }
//            )
//        }
//    }

//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun YourProfilePreview() {
//        YourProfile()
//    }


