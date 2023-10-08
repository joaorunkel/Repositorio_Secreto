package com.confradestech.waterly.datasources.models

data class WaterSampleParameters(
    val id: String = "",
    val human_risk_concern: Int = 0,
    val parameter_code: String = "",
    val parameter_description: String = "",
    val parameter_long_name: String = "",
    val ranked: Boolean = false,
)