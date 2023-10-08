package com.confradestech.waterly.presentation.home

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confradestech.waterly.datasources.models.FaunaEntry
import com.confradestech.waterly.datasources.models.FloraEntry
import com.confradestech.waterly.datasources.models.WaterEntry
import com.confradestech.waterly.datasources.models.WaterSample
import com.confradestech.waterly.datasources.models.WaterSampleParameters
import com.confradestech.waterly.datasources.states.HomeFragmentState
import com.confradestech.waterly.datasources.states.SelectedWaterDataState
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_FAUNA_DATA
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_FLORA_DATA
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_PARAMETERS
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_SAMPLES
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_WATERLY_DATA
import com.confradestech.waterly.utilites.CommonConstants.INVALID_DOUBLE
import com.confradestech.waterly.utilites.CommonConstants.INVALID_LAT_LNG
import com.confradestech.waterly.utilites.CommonConstants.requestDispatchers
import com.confradestech.waterly.utilites.extensions.toDate
import com.firebase.geofire.GeoFireUtils
import com.firebase.geofire.GeoLocation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    var homeFragmentState by mutableStateOf(HomeFragmentState())
        private set

    var selectedWaterEntryState by mutableStateOf(SelectedWaterDataState())
        private set

    private val lock = Any()

    fun searchNearWaterEntries(radiusToSearch: Float) {
        viewModelScope.launch(requestDispatchers) {
            updateLoadingState(true)

            val radiusInM = radiusToSearch * 1000.0

            val waterEntries = mutableListOf<WaterEntry>()
            val matchingDocs: MutableList<DocumentSnapshot> = ArrayList()
            val tasks: MutableList<Task<QuerySnapshot>> = ArrayList()

            val userLocation = GeoLocation(
                homeFragmentState.userLastLocation?.latitude ?: 0.0,
                homeFragmentState.userLastLocation?.longitude ?: 0.0
            )

            val bounds = GeoFireUtils.getGeoHashQueryBounds(userLocation, radiusInM)
            bounds.forEach {
                val query = firestore.collection(FIRESTORE_WATERLY_DATA)
                    .orderBy("geoHash")
                    .startAt(it.startHash)
                    .endAt(it.endHash)
                tasks.add(query.get())
            }

            Tasks.whenAllComplete(tasks)
                .addOnCompleteListener {
                    for (task in tasks) {
                        task.result.documents.forEach { doc ->
                            val lat = doc.getDouble("lat")!!
                            val lng = doc.getDouble("lng")!!
                            val docLocation = GeoLocation(lat, lng)
                            val distanceInM =
                                GeoFireUtils.getDistanceBetween(docLocation, userLocation)
                            if (distanceInM <= radiusInM) {
                                matchingDocs.add(doc)
                            }
                        }
                    }
                    if (matchingDocs.isEmpty()) {
                        showEmptySearchFeedback(true)
                    } else {
                        matchingDocs.forEach { document ->
                            val waterEntry = document.toObject<WaterEntry>()
                            waterEntry?.let {
                                waterEntries.add(
                                    it.copy(
                                        id = document.id
                                    )
                                )
                            }
                            updateWaterEntriesState(waterEntries)
                        }
                    }
                }.addOnFailureListener { exception ->
                    updateErrorState(exception)
                }
        }
    }

    fun searchNearFaunaEntries(radiusToSearch: Float) {
        viewModelScope.launch(requestDispatchers) {
            updateLoadingState(true)

            val radiusInM = radiusToSearch * 1000.0

            val faunaEntries = mutableListOf<FaunaEntry>()
            val matchingDocs: MutableList<DocumentSnapshot> = ArrayList()
            val tasks: MutableList<Task<QuerySnapshot>> = ArrayList()

            val userLocation = GeoLocation(
                homeFragmentState.userLastLocation?.latitude ?: 0.0,
                homeFragmentState.userLastLocation?.longitude ?: 0.0
            )

            val bounds = GeoFireUtils.getGeoHashQueryBounds(userLocation, radiusInM)
            bounds.forEach {
                val query = firestore.collection(FIRESTORE_FAUNA_DATA)
                    .orderBy("geoHash")
                    .startAt(it.startHash)
                    .endAt(it.endHash)
                tasks.add(query.get())
            }

            Tasks.whenAllComplete(tasks)
                .addOnCompleteListener {
                    for (task in tasks) {
                        task.result.documents.forEach { doc ->
                            val lat = doc.getDouble("latitude")!!
                            val lng = doc.getDouble("longitude")!!
                            val docLocation = GeoLocation(lat, lng)
                            val distanceInM =
                                GeoFireUtils.getDistanceBetween(docLocation, userLocation)
                            if (distanceInM <= radiusInM) {
                                matchingDocs.add(doc)
                            }
                        }
                    }
                    if (matchingDocs.isEmpty()) {
                        showEmptySearchFeedback(true)
                    } else {
                        matchingDocs.forEach { document ->
                            val faunaEntry = document.toObject<FaunaEntry>()
                            faunaEntry?.let {
                                faunaEntries.add(
                                    it.copy(
                                        id = document.id
                                    )
                                )
                            }
                            updateFaunaEntriesState(faunaEntries)
                        }
                    }
                }.addOnFailureListener { exception ->
                    updateErrorState(exception)
                }
        }
    }

    fun searchNearFloraEntries(radiusToSearch: Float) {
        viewModelScope.launch(requestDispatchers) {
            updateLoadingState(true)

            val radiusInM = radiusToSearch * 1000.0

            val floraEntries = mutableListOf<FloraEntry>()
            val matchingDocs: MutableList<DocumentSnapshot> = ArrayList()
            val tasks: MutableList<Task<QuerySnapshot>> = ArrayList()

            val userLocation = GeoLocation(
                homeFragmentState.userLastLocation?.latitude ?: 0.0,
                homeFragmentState.userLastLocation?.longitude ?: 0.0
            )

            val bounds = GeoFireUtils.getGeoHashQueryBounds(userLocation, radiusInM)
            bounds.forEach {
                val query = firestore.collection(FIRESTORE_FLORA_DATA)
                    .orderBy("geoHash")
                    .startAt(it.startHash)
                    .endAt(it.endHash)
                tasks.add(query.get())
            }

            Tasks.whenAllComplete(tasks)
                .addOnCompleteListener {
                    for (task in tasks) {
                        task.result.documents.forEach { doc ->
                            val lat = doc.getString("latitude") ?: "0.0"
                            val lng = doc.getString("longitude") ?: "0.0"
                            val docLocation =
                                GeoLocation(
                                    lat.toDoubleOrNull() ?: INVALID_DOUBLE,
                                    lng.toDoubleOrNull() ?: INVALID_DOUBLE
                                )
                            val distanceInM =
                                GeoFireUtils.getDistanceBetween(docLocation, userLocation)
                            if (distanceInM <= radiusInM) {
                                matchingDocs.add(doc)
                            }
                        }
                    }
                    if (matchingDocs.isEmpty()) {
                        showEmptySearchFeedback(true)
                    } else {
                        matchingDocs.forEach { document ->
                            val floraEntry = document.toObject<FloraEntry>()
                            floraEntry?.let {
                                floraEntries.add(
                                    it.copy(
                                        id = document.id
                                    )
                                )
                            }
                            updateFloraEntriesState(floraEntries)
                        }
                    }
                }.addOnFailureListener { exception ->
                    updateErrorState(exception)
                }
        }
    }

    fun showEmptySearchFeedback(showEmptyFeedback: Boolean) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                showEmptyFeedback = showEmptyFeedback
            )
            updateLoadingState(false)
        }
    }

    private fun updateWaterEntriesState(waterEntries: List<WaterEntry>?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                waterEntryList = waterEntries
            )
            updateLoadingState(false)
        }
    }

    private fun updateFaunaEntriesState(faunaEntries: List<FaunaEntry>?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                faunaEntryList = faunaEntries
            )
            updateLoadingState(false)
        }
    }

    private fun updateFloraEntriesState(floraEntries: List<FloraEntry>?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                floraEntryList = floraEntries
            )
            updateLoadingState(false)
        }
    }

    private fun updateLoadingState(isLoading: Boolean?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                isLoading = isLoading
            )
        }
    }

    fun updatePermissionsState(havePermissions: Boolean?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                havePermissions = havePermissions
            )
        }
    }

    private fun updateErrorState(error: Throwable?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                waterEntryList = null,
                error = error
            )
            updateLoadingState(false)
        }
    }

    fun setLastLocation(location: Location?) {
        synchronized(lock) {
            homeFragmentState = homeFragmentState.copy(
                userLastLocation = location
            )
        }
    }

    fun setSelectedWaterEntry(waterEntry: WaterEntry?) {
        updateWaterDataEntryState(waterEntry)
        searchWaterDataSamples(waterEntry?.id)
    }

    private fun searchWaterDataSamples(waterEntryId: String?) {
        viewModelScope.launch(requestDispatchers) {
            updateWaterDataDetailsLoadingState(true)
            val waterDataSamples = mutableListOf<WaterSample>()
            firestore.collection(FIRESTORE_SAMPLES)
                .whereEqualTo("gems_station_number", waterEntryId)
                .get()
                .addOnSuccessListener { result ->
                    updateWaterDataDetailsLoadingState(false)
                    result.forEach { document ->
                        val waterSample = document.toObject<WaterSample>()
                        waterDataSamples.add(
                            waterSample.copy(
                                id = document.id,
                                translatedDate = waterSample.sample_date.toDate()
                            )
                        )
                    }
                    waterDataSamples.sortBy { it.translatedDate }
                    waterDataSamples.firstOrNull()?.let {
                        updateWaterDataEntrySamplesState(listOf(it))
                        searchSamplesParameters(it.parameter_code)
                    }
                }
                .addOnFailureListener { exception ->
                    updateWaterDataEntryErrorState(exception)
                }
        }
    }

    private fun searchSamplesParameters(parameterCode: String) {
        viewModelScope.launch(requestDispatchers) {
            updateWaterDataDetailsLoadingState(true)
            val waterDataParameters = mutableListOf<WaterSampleParameters>()
            firestore.collection(FIRESTORE_PARAMETERS)
                .whereEqualTo("parameter_code", parameterCode)
                .get()
                .addOnSuccessListener { result ->
                    updateWaterDataDetailsLoadingState(false)
                    result.forEach { document ->
                        val waterSampleParameters = document.toObject<WaterSampleParameters>()
                        waterDataParameters.add(
                            waterSampleParameters.copy(
                                id = document.id
                            )
                        )
                    }
                    updateWaterDataEntrySamplesParametersState(waterDataParameters)
                }
                .addOnFailureListener { exception ->
                    updateWaterDataEntryErrorState(exception)
                }
        }
    }

    private fun updateWaterDataDetailsLoadingState(isLoading: Boolean?) {
        synchronized(lock) {
            selectedWaterEntryState = selectedWaterEntryState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun updateWaterDataEntryState(waterEntry: WaterEntry?) {
        synchronized(lock) {
            selectedWaterEntryState = selectedWaterEntryState.copy(
                waterEntry = waterEntry
            )
            updateWaterDataDetailsLoadingState(false)
        }
    }

    private fun updateWaterDataEntrySamplesState(waterSamples: List<WaterSample>?) {
        synchronized(lock) {
            selectedWaterEntryState = selectedWaterEntryState.copy(
                waterSamples = waterSamples
            )
            updateWaterDataDetailsLoadingState(false)
        }
    }

    private fun updateWaterDataEntrySamplesParametersState(waterSamplesParameters: List<WaterSampleParameters>?) {
        synchronized(lock) {
            selectedWaterEntryState = selectedWaterEntryState.copy(
                waterSamplesParameters = waterSamplesParameters
            )
            updateWaterDataDetailsLoadingState(false)
        }
    }

    private fun updateWaterDataEntryErrorState(error: Throwable?) {
        synchronized(lock) {
            selectedWaterEntryState = selectedWaterEntryState.copy(
                error = error
            )
            updateWaterDataDetailsLoadingState(false)
        }
    }

}
