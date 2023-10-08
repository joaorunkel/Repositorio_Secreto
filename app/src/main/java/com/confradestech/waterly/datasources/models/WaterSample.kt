package com.confradestech.waterly.datasources.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class WaterSample(
    val id: String = "",
    val gems_station_number: String = "",
    val parameter_code: String = "",
    val sample_date: String = "",
    val translatedDate: Date? = Date(),
    val unit: String = "",
    val value: Long = 0,
)