package com.confradestech.waterly.datasources.states

import com.confradestech.waterly.datasources.models.FaunaEntry
import com.confradestech.waterly.datasources.models.FloraEntry
import com.google.android.gms.maps.model.LatLng

data class FloraMarkerInfoState (
    val floraEntry: FloraEntry? = null,
    val markerPosition: LatLng? = null
)