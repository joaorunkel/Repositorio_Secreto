package com.confradestech.waterly.utilites.components.commom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.confradestech.waterly.R
import com.confradestech.waterly.theme.WaterlyTheme

@Composable
fun GenericDialog(
    genericTitleId: Int = R.string.generic_full_screen_dialog_title,
    genericButtonSaveTextId: Int = R.string.generic_full_screen_save_button_title,
    isFullScreen: Boolean = true,
    showSaveOption: Boolean = true,
    showExitIcon: Boolean = true,
    isPermissionsDialog: Boolean = false,
    isDeletePhotoDialog: Boolean = false,
    onDismiss: () -> Unit,
    onSaveButtonTapped: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(
        properties = if (isPermissionsDialog) {
            DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        } else {
            DialogProperties(usePlatformDefaultWidth = false)
        },
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = if (isFullScreen) {
                Modifier.fillMaxSize(0.95F)
            } else {
                Modifier.fillMaxWidth(0.95F)
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_8)),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = if (isFullScreen || isPermissionsDialog || isDeletePhotoDialog) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6F)
                },
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopStart),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.primaryContainer),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (showExitIcon) {
                            Icon(
                                modifier = Modifier.clickable { onDismiss() },
                                painter = painterResource(id = R.drawable.baseline_close_24),
                                contentDescription = stringResource(id = R.string.generic_close_description)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(
                                    top = dimensionResource(id = R.dimen.spacing_10),
                                    bottom = dimensionResource(id = R.dimen.spacing_10),
                                )
                                .then(
                                    if (showSaveOption) {
                                        Modifier.width(dimensionResource(id = R.dimen.spacing_160))
                                    } else if (isPermissionsDialog || isDeletePhotoDialog) {
                                        Modifier.fillMaxWidth()
                                    } else {
                                        Modifier
                                    }
                                ),
                            overflow = TextOverflow.Ellipsis,
                            textAlign = if (showSaveOption ||
                                isPermissionsDialog ||
                                isDeletePhotoDialog
                            ) {
                                TextAlign.Center
                            } else {
                                TextAlign.Start
                            },
                            softWrap = true,
                            fontSize = 18.sp,
                            text = stringResource(id = genericTitleId),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        if (showSaveOption) {
                            TextButton(
                                onClick = { onSaveButtonTapped() },
                            ) {
                                Text(
                                    fontSize = 16.sp,
                                    text = stringResource(id = genericButtonSaveTextId),
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = dimensionResource(id = R.dimen.spacing_1),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.spacing_10),
                                start = dimensionResource(id = R.dimen.spacing_20),
                                end = dimensionResource(id = R.dimen.spacing_20),
                                bottom = dimensionResource(id = R.dimen.spacing_20),
                            ),
                    ) {
                        content()
                    }
                }
            }
        }
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
private fun GenericFullScreenDialog_Preview() {
    WaterlyTheme {
        GenericDialog(
            onDismiss = { /*NO-OP for preview */ },
            onSaveButtonTapped = { /*NO-OP for preview */ },
            content = {
                repeat(200) {
                    Text("Content item $it")
                }
            }
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
private fun GenericNoFullScreenDialog_Preview() {
    WaterlyTheme {
        GenericDialog(
            onDismiss = { /*NO-OP for preview */ },
            onSaveButtonTapped = { /*NO-OP for preview */ },
            isFullScreen = false,
            content = {
                repeat(200) {
                    Text("Content item $it")
                }
            }
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
private fun GenericPermissionsDialog_Preview() {
    WaterlyTheme {
        GenericDialog(
            onDismiss = { /*NO-OP for preview */ },
            onSaveButtonTapped = { /*NO-OP for preview */ },
            isFullScreen = false,
            showSaveOption = false,
            showExitIcon = false,
            isPermissionsDialog = true,
            content = {
                repeat(5) {
                    Text("Content item $it")
                }
            }
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
private fun GenericDeletePhotoDialog_Preview() {
    WaterlyTheme {
        GenericDialog(
            onDismiss = { /*NO-OP for preview */ },
            onSaveButtonTapped = { /*NO-OP for preview */ },
            isFullScreen = false,
            showSaveOption = false,
            showExitIcon = false,
            isDeletePhotoDialog = true,
            content = {
                repeat(5) {
                    Text("Content item $it")
                }
            }
        )
    }
}
//endregion previews
