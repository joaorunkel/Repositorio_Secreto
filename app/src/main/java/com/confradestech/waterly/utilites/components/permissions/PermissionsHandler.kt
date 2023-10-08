package com.confradestech.waterly.utilites.components.permissions

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.confradestech.waterly.theme.WaterlyTheme
import com.confradestech.waterly.utilites.components.commom.LightModeBigScreensLandscape
import com.confradestech.waterly.utilites.components.commom.LightModeBigScreensPortrait
import com.confradestech.waterly.utilites.components.commom.LightModeStandardScreens
import com.confradestech.waterly.utilites.components.commom.NightModeBigScreensLandscape
import com.confradestech.waterly.utilites.components.commom.NightModeBigScreensPortrait
import com.confradestech.waterly.utilites.components.commom.NightModeStandardScreens
import com.confradestech.waterly.utilites.extensions.showAppSettings

@Composable
fun PermissionHandler(
    permissions: List<String>,
    shouldShowDialog: Boolean,
    shouldShowRationale: Boolean,
    onPermissionGratend: () -> Unit,
) {

    val context = LocalContext.current
    val showDialog = rememberSaveable { mutableStateOf<Boolean?>(shouldShowDialog) }
    val showRationale = rememberSaveable { mutableStateOf<Boolean?>(shouldShowRationale) }

    val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissionResults ->
            val permissionDenied = permissionResults.any { !it.value }
            if (!permissionDenied) {
                showDialog.value = false
                onPermissionGratend()
            } else {
                showRationale.value = true
            }

        }
    )

    if (showDialog.value == true) {
        PermissionDialog(
            isPermanentlyDeclined = showRationale.value ?: false,
            onDismiss = { showDialog.value = false },
            onOkClick = {
                multiplePermissionResultLauncher.launch(
                    permissions.toTypedArray()
                )
            },
            onGoToAppSettingsClick = { context.showAppSettings() }
        )
    }
}

//region previews
@Composable
@LightModeStandardScreens
@NightModeStandardScreens
@LightModeBigScreensPortrait
@NightModeBigScreensPortrait
@LightModeBigScreensLandscape
@NightModeBigScreensLandscape
private fun PermissionHandlerNoRationale_Preview() {
    WaterlyTheme {
        PermissionHandler(
            permissions = permissionsPreviewAsset,
            shouldShowRationale = false,
            shouldShowDialog = true,
            onPermissionGratend = { /*No-op for previews */}
        )
    }
}

@Composable
@LightModeStandardScreens
@NightModeStandardScreens
@LightModeBigScreensPortrait
@NightModeBigScreensPortrait
@LightModeBigScreensLandscape
@NightModeBigScreensLandscape
private fun PermissionHandlerRationale_Preview() {
    WaterlyTheme {
        PermissionHandler(
            permissions = permissionsPreviewAsset,
            shouldShowRationale = true,
            shouldShowDialog = true,
            onPermissionGratend = { /*No-op for previews */}
        )
    }
}
//endregion previews


