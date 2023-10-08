package com.confradestech.waterly.datasources.states

import com.confradestech.waterly.datasources.models.FaunaEntry
import com.google.android.gms.maps.model.LatLng

data class FaunaMarkerInfoState (
    val faunaEntry: FaunaEntry? = null,
    val markerPosition: LatLng? = null
)