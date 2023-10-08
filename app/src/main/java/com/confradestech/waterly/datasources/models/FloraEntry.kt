package com.confradestech.waterly.datasources.models

import com.google.gson.annotations.SerializedName

data class FloraEntry(
    val id: String = "",
    val geoHash: String = "",
    val latitude: String = "0.0",
    val longitude: String = "0.0",
    val record_id: String = "",
    val scientific_name: String = "",
    val state_province: String = "",
    val vernacular_name: String = "",
)