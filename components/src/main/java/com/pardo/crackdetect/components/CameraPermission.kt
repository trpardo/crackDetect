package com.pardo.crackdetect.components

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionRequest(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    when (cameraPermissionState.status) {
        PermissionStatus.Granted -> onPermissionGranted()
        is PermissionStatus.Denied -> {
            val shouldShowRationale = (cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale.not()

            CameraPermissionRationaleDialog(
                showDialog = shouldShowRationale,
                onConfirm = { cameraPermissionState.launchPermissionRequest() },
                onDismiss = onPermissionDenied
            )

            SettingsAlertDialog(
                showDialog = !shouldShowRationale,
                onDismiss = onPermissionDenied
            )
        }
    }
}
