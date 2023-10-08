package com.confradestech.waterly.datasources.states

import com.confradestech.waterly.datasources.models.WaterEntry
import com.confradestech.waterly.datasources.models.WaterSample
import com.confradestech.waterly.datasources.models.WaterSampleParameters

data class SelectedWaterDataState(
    val waterEntry: WaterEntry? = null,
    val waterSamples: List<WaterSample>? = null,
    val waterSamplesParameters: List<WaterSampleParameters>? = null,
    val isLoading: Boolean? = false,
    val error: Throwable? = null
)
