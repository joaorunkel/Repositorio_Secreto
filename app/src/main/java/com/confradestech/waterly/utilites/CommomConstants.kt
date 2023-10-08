package com.confradestech.waterly.utilites

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object CommonConstants {

    const val FIRESTORE_WATERLY_DATA = "BR_waterly_data"
    const val FIRESTORE_FAUNA_DATA = "fauna"
    const val FIRESTORE_FLORA_DATA = "flora"
    const val FIRESTORE_SAMPLES = "samples"
    const val FIRESTORE_PARAMETERS = "parameters"

    const val AS_DIS = "As-Dis"
    const val As_Tot = "As-Tot"
    const val Cd_Dis = "Cd-Dis"
    const val Cd_Tot = "Cd-Tot"
    const val CN_Tot = "CN-Tot"
    const val Cr_Dis = "Cr-Dis"
    const val Cr_Tot = "Cr-Tot"
    const val Cr_VI = "Cr-VI"
    const val DIELDRIN = "DIELDRIN"
    const val ENDRIN = "ENDRIN"
    const val FECALCOLI = "FECALCOLI"
    const val FECALSTREP = "FECALSTREP"
    const val Hg_Dis = "Hg-Dis"
    const val Hg_Tot = "Hg-Tot "
    const val O2_Dis = "O2-Dis"
    const val Pb_Dis = "Pb-Dis"
    const val Pb_Tot = "Pb-Tot"
    const val TURB = "TURB"
    const val PH = "PH"

    const val PH_high = 10
    const val PH_low = 5

    val requestDispatchers: CoroutineDispatcher = Dispatchers.IO

    const val MAPS_ZOOM = 12F

    val INVALID_LAT_LNG = LatLng(0.0, 0.0)
    val INVALID_DOUBLE = 0.0

}