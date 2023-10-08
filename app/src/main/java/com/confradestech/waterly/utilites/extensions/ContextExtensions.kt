package com.confradestech.waterly.utilites.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.content.res.AppCompatResources

fun Context.hasInternet(): Boolean {

    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                || capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo

        if (activeNetworkInfo != null &&
            connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
        ) {
            return with(activeNetworkInfo.type) {
                this == ConnectivityManager.TYPE_WIFI
                this == ConnectivityManager.TYPE_MOBILE
            }
        } else {
            false
        }
    }
}

fun Context.showAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun Context.drawableToBitmap(drawableResId: Int): Bitmap {
    // Get the Drawable from the resource ID
    val drawable: Drawable? = AppCompatResources.getDrawable(this, drawableResId)

    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }

    // If the Drawable is not a BitmapDrawable, create a new Bitmap and draw the Drawable on it
    val bitmap: Bitmap = Bitmap.createBitmap(
        drawable?.intrinsicWidth ?: 1,
        drawable?.intrinsicHeight ?: 1,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
    drawable?.setBounds(0, 0, canvas.width, canvas.height)
    drawable?.draw(canvas)

    return bitmap
}




