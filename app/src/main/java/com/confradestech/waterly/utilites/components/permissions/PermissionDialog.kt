package com.confradestech.waterly.utilites.components.permissions


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.confradestech.waterly.R
import com.confradestech.waterly.utilites.components.commom.GenericDialog

@Composable
fun PermissionDialog(
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
) {
    GenericDialog(
        genericTitleId = R.string.permissions_dialog_title,
        onDismiss = {
            onDismiss()
        },
        isFullScreen = false,
        showSaveOption = false,
        showExitIcon = false,
        isPermissionsDialog = true,
        onSaveButtonTapped = { /*NO-Op */ },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isPermanentlyDeclined) {
                        stringResource(id = R.string.permissions_dialog_permanently_rational_text_body)
                    } else {
                        stringResource(id = R.string.permissions_dialog_text_body)
                    },
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_10)))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (isPermanentlyDeclined) {
                            onGoToAppSettingsClick()
                        } else {
                            onOkClick()
                        }
                    }) {
                    if (isPermanentlyDeclined) {
                        Text(
                            text = stringResource(id = R.string.permissions_dialog_permanently_deny)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.dialog_positive_button)
                        )
                    }
                }
            }
        }
    )
}
