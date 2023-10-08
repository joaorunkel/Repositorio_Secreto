package com.confradestech.waterly.utilites

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object CommonConstants {

    const val FIRESTORE_WATERLY_DATA = "BR_waterly_data"
    const val FIRESTORE_FAUNA_DATA = "fauna"
    const val FIRESTORE_FLORA_DATA = "flora"

    val requestDispatchers: CoroutineDispatcher = Dispatchers.IO

    const val MAPS_ZOOM = 12F

    val INVALID_LAT_LNG = LatLng(0.0, 0.0)
    val INVALID_DOUBLE = 0.0

}