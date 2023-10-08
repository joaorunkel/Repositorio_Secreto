package com.confradestech.waterly.utilites.components.permissions

import android.Manifest
import android.os.Build

fun getRequiredPermissions(): List<String> {
    val permissions = mutableListOf<String>()

    //Commom permissions
    permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)

    // Post notification permission
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions.add(Manifest.permission.POST_NOTIFICATIONS)
    }

    return permissions
}
