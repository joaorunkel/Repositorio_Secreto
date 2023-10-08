package com.confradestech.waterly.presentation.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.confradestech.waterly.R
import com.confradestech.waterly.presentation.home.screen.HomeScreen
import com.confradestech.waterly.utilites.components.permissions.PermissionHandler
import com.confradestech.waterly.utilites.components.permissions.getRequiredPermissions
import com.confradestech.waterly.theme.WaterlyTheme
import com.confradestech.waterly.utilites.CommonConstants
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_FAUNA_DATA
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_FLORA_DATA
import com.confradestech.waterly.utilites.CommonConstants.FIRESTORE_WATERLY_DATA
import com.confradestech.waterly.utilites.ConnectivityChecker
import com.confradestech.waterly.utilites.extensions.showAppSettings
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var connectivityChecker: ConnectivityChecker


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireActivity()).apply {
            setContent {

                fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(requireActivity())

                val connectionErrorMessage =
                    stringResource(id = R.string.home_screen_emptySearchFeedback)

                WaterlyTheme {

                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        viewModel.updatePermissionsState(false)
                        CheckPermissionsDialog()
                    } else {
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location: Location? ->
                                viewModel.setLastLocation(location)
                            }
                        viewModel.updatePermissionsState(true)
                    }

                    if (viewModel.homeFragmentState.showEmptyFeedback == true) {
                        Toast.makeText(
                            requireContext(),
                            stringResource(id = R.string.home_screen_emptySearchFeedback),
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.showEmptySearchFeedback(false)
                    }

                    HomeScreen(
                        homeState = viewModel.homeFragmentState,
                        searchEntries = { radiusToSearch, listToSearch ->
                            if (!connectivityChecker.isConnected()) {
                                Toast.makeText(
                                    requireContext(),
                                    connectionErrorMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (listToSearch.contains(FIRESTORE_WATERLY_DATA)) {
                                    viewModel.searchNearWaterEntries(radiusToSearch)
                                }
                                if (listToSearch.contains(FIRESTORE_FLORA_DATA)) {
                                    viewModel.searchNearFloraEntries(radiusToSearch)
                                }
                                if (listToSearch.contains(FIRESTORE_FAUNA_DATA)) {
                                    viewModel.searchNearFaunaEntries(radiusToSearch)
                                }
                            }
                        },
                        openPhoneSettings = {
                            requireContext().showAppSettings()
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun CheckPermissionsDialog() {
        val requiredPermissions = getRequiredPermissions()

        PermissionHandler(
            permissions = requiredPermissions,
            shouldShowRationale = requiredPermissions.any { permission ->
                shouldShowRequestPermissionRationale(
                    /* activity = */ requireActivity(),
                    /* permission = */ permission
                )
            },
            shouldShowDialog = requiredPermissions.any { permission ->
                (ContextCompat.checkSelfPermission(
                    /* context = */ requireContext(),
                    /* permission = */ permission
                ) != PackageManager.PERMISSION_GRANTED)
            },
            onPermissionGratend = {
                viewModel.updatePermissionsState(true)
            }
        )
    }

}

