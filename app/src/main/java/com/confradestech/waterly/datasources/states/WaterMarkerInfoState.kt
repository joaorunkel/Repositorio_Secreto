package com.confradestech.waterly.datasources.states

import com.confradestech.waterly.datasources.models.WaterEntry
import com.google.android.gms.maps.model.LatLng

data class WaterMarkerInfoState (
    val waterEntry: WaterEntry? = null,
    val markerPosition: LatLng? = null
)