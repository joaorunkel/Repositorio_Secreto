package com.confradestech.waterly.datasources.models

import com.google.gson.annotations.SerializedName

data class FaunaEntry(
    val id: String = "",
    val geoHash: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val record_id: String = "",
    val scientific_name: String = "",
    val state_province: String = "",
    val vernacular_name: String = "",
    val type: String = ""
)