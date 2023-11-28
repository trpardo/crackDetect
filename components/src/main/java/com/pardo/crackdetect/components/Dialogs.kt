package com.pardo.crackdetect.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.pardo.crackdetect.theme.R

@Composable
fun CameraPermissionRationaleDialog(
    showDialog: Boolean = true,
    onConfirm: (() -> Unit)?,
    onDismiss: (() -> Unit)?
) {
    BaseAlertDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.app_permission_request_camera_title),
        text = stringResource(id = R.string.app_permission_request_camera_rationale_text),
        confirmButtonText = stringResource(id = R.string.app_button_retry),
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}

@Composable
fun SettingsAlertDialog(
    showDialog: Boolean = true,
    onDismiss: (() -> Unit)?
) {
    val context = LocalContext.current

    BaseAlertDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.app_permission_request_camera_title),
        text = stringResource(id = R.string.app_permission_request_camera_settings_text),
        confirmButtonText = stringResource(id = R.string.app_button_settings),
        onConfirm = {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            startActivity(context, intent, null)
        },
        onDismiss = onDismiss
    )
}

@Composable
fun BaseAlertDialog(
    showDialog: Boolean = false,
    title: String = "",
    text: String = "",
    confirmButtonText: String = "",
    onConfirm: (() -> Unit)? = null,
    dismissButtonText: String = stringResource(id = R.string.app_button_cancel),
    onDismiss: (() -> Unit)? = null
) {
    val showState = remember { mutableStateOf(showDialog) }

    if (showState.value) {
        AlertDialog(
            title = { Titles.SimpleTitle(text = title) },
            text = { Titles.SimpleDescription(text = text) },
            onDismissRequest = {
                showState.value = false
                onDismiss?.invoke()
            },
            confirmButton = {
                Buttons.Filled(
                    modifier = Modifier.fillMaxWidth(),
                    text = confirmButtonText,
                    onClick = {
                        showState.value = false
                        onConfirm?.invoke()
                    }
                )
            },
            dismissButton = {
                Buttons.Outline(
                    modifier = Modifier.fillMaxWidth(),
                    text = dismissButtonText,
                    onClick = {
                        showState.value = false
                        onDismiss?.invoke()
                    }
                )
            }
        )
    }
}
