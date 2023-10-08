package com.confradestech.waterly.datasources.states

import android.location.Location
import com.confradestech.waterly.datasources.models.FaunaEntry
import com.confradestech.waterly.datasources.models.FloraEntry
import com.confradestech.waterly.datasources.models.WaterEntry

data class HomeFragmentState(
    val waterEntryList: List<WaterEntry>? = null,
    val faunaEntryList: List<FaunaEntry>? = null,
    val floraEntryList: List<FloraEntry>? = null,
    val isLoading: Boolean? = false,
    val showEmptyFeedback: Boolean? = false,
    val havePermissions: Boolean? = null,
    val userLastLocation: Location? = null,
    val error: Throwable? = null
)
