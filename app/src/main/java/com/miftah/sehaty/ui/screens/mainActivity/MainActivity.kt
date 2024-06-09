package com.miftah.sehaty.ui.screens.mainActivity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miftah.sehaty.ui.screens.scan.ScanScreen
import com.miftah.sehaty.ui.theme.SehatyTheme
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.miftah.sehaty.utils.CameraPermissionTextProvider
import com.miftah.sehaty.utils.PermissionDialog

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SehatyTheme {
                val viewModel = viewModel<MainViewModel>()
                val dialogQueue = viewModel.visiblePermissionDialogQueue

                val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        viewModel.onPermissionResult(
                            permission = Manifest.permission.CAMERA,
                            isGranted = isGranted
                        )
                    }
                )

                dialogQueue
                    .reversed()
                    .forEach { permission ->
                        PermissionDialog(
                            permissionTextProvider = when (permission) {
                                Manifest.permission.CAMERA -> {
                                    CameraPermissionTextProvider()
                                }

                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                permission
                            ),
                            onDismiss = viewModel::dismissDialog,
                            onOkClick = {
                                viewModel.dismissDialog()
                            },
                            onGoToAppSettingsClick = ::openAppSettings
                        )
                    }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    cameraPermissionResultLauncher.launch(
                        Manifest.permission.CAMERA
                    )
                    if (viewModel.isPermissionAcquire.value) {
                        ScanScreen()
                    }
                }
            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}