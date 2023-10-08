package com.confradestech.waterly.utilites

import android.app.Application
import com.confradestech.waterly.utilites.extensions.hasInternet

class ConnectivityChecker(private val application: Application) {

    fun isConnected(): Boolean {
        return application.hasInternet()
    }

}