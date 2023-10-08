package com.confradestech.waterly.presentation.waterDataDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import com.confradestech.waterly.presentation.home.HomeViewModel
import com.confradestech.waterly.presentation.waterDataDetails.screen.WaterDataDetailsScreen
import com.confradestech.waterly.theme.WaterlyTheme


class WaterDataDetailsFragment : Fragment() {

    private val viewModel by activityViewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireActivity()).apply {
            setContent {
                WaterlyTheme {
                    WaterDataDetailsScreen(viewModel.selectedWaterEntryState)
                }
            }
        }
    }
}