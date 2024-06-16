package com.miftah.sehaty.ui.screens.scan

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.miftah.sehaty.ui.screens.common.ButtonPrimary
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens
import com.miftah.sehaty.utils.reduceFileImage
import com.miftah.sehaty.utils.reduceFileImageCompat
import com.miftah.sehaty.utils.saveBitmapToFile
import com.miftah.sehaty.utils.uriToFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(modifier: Modifier = Modifier) {
    var currentProgress by remember { mutableStateOf(0f) }

    val itemName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val saveToUri: (Uri?) -> Unit = { uri: Uri? ->
        imageUri = uri
        if (uri != null) {
            val imageFile = uriToFile(uri, context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imageFile.reduceFileImage()
                Log.d("URI -> FILE", imageFile.path)
            } else {
                val reducedFile = imageFile.reduceFileImageCompat()
                Log.d("URI -> FILE (Compat)", reducedFile.path)
            }
            scope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent(),
        onResult = saveToUri
    )
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }

    val imageCropLauncher =
        rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
            if (result.isSuccessful) {
                saveToUri(result.uriContent)
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            } else {
                println("ImageCropping error: ${result.error}")
            }
        }

    val cropOptions =
        CropImageContractOptions(imageUri, CropImageOptions(imageSourceIncludeCamera = true))

    if (imageBitmap.value != null || imageUri != null) {
        BottomSheetResult(
            modifier,
            scaffoldState,
            imageBitmap,
            imageUri,
            context,
            itemName,
            onCropAction = {
                imageCropLauncher.launch(cropOptions)
            }
        ) {}

    } else {
        CameraXExecutors(
            modifier,
            controller,
            launcher,
            context,
            saveToUri,
        )
    }
}

object CameraPermissions {
    val CAMERAX_PERMISSION = arrayOf(Manifest.permission.CAMERA)
}

fun hasRequiredPermissions(context: Context): Boolean {
    return CameraPermissions.CAMERAX_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetResult(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    imageBitmap: MutableState<Bitmap?>,
    imageUri: Uri?,
    context: Context,
    itemName: String,
    onCropAction: (() -> Unit),
    onItemName: ((String) -> Unit)
) {
    BottomSheetScaffold(
        modifier = modifier.windowInsetsPadding(WindowInsets.ime),
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier.padding(16.dp)
            ) {
                Text(text = "Masukan Nama Makanan")
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.small2)
                )
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = itemName,
                    onValueChange = onItemName
                )
                Spacer(modifier = modifier.height(MaterialTheme.dimens.small2))
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    ButtonPrimary(
                        modifier = modifier
                            .height(MaterialTheme.dimens.buttonHeight).weight(1f),
                        title = "NEXT",
                    ) {
                    }
                    Spacer(modifier = modifier.width(8.dp))
                    ButtonPrimary(
                        modifier = modifier
                            .height(MaterialTheme.dimens.buttonHeight).weight(1f),
                        title = "CROP",
                        onAction = onCropAction
                    )
                }
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            imageBitmap.value?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                }
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        imageBitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)

                    } else {
                        val source = ImageDecoder
                            .createSource(context.contentResolver, it)
                        imageBitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    imageBitmap.value?.let { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CameraXExecutors(
    modifier: Modifier = Modifier,
    controller: LifecycleCameraController,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    context: Context,
    saveToUri: (Uri?) -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CameraX(controller = controller, modifier = modifier.fillMaxSize())
            Column(
                modifier = modifier.align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = {
                        launcher.launch("image/*")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = "Open Gallery",
                        )
                    }
                    IconButton(onClick = {
                        takePhoto(
                            controller = controller,
                            context = context,
                            onPhotoTaken = { bitmap ->
                                val savedUri = saveBitmapToFile(context, bitmap)
                                saveToUri(savedUri)
                            }
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Take Photo "
                        )
                    }
                }
                Spacer(
                    modifier = modifier
                        .height(MaterialTheme.dimens.medium2)
                        .fillMaxWidth()
                )
            }
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
) {
    if (!hasRequiredPermissions(context)) {
        return
    }
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                    postScale(-1f, 1f)
                }
                val rotatedBitMap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                onPhotoTaken(rotatedBitMap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.d("Camera ", "Could't take photo", exception)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewScanScreen() {
    val itemName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) }
    val imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    LaunchedEffect(true)  {
        scaffoldState.bottomSheetState.expand()
    }
    SehatyTheme {
        BottomSheetResult(
            onCropAction = {},
            onItemName = {},
            context = context,
            scaffoldState = scaffoldState,
            imageBitmap = imageBitmap,
            imageUri = imageUri,
            itemName = itemName
        )
    }
}


