package com.confradestech.waterly.datasources.models

import com.google.gson.annotations.SerializedName

data class WaterEntry(
    val id: String = "",
    val stationIdentifier: String = "",
    val lng: Double = 0.0,
    val lat: Double = 0.0,
    val geoHash: String = "",
    val waterType: String = ""
)