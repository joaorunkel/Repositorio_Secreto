package com.confradestech.waterly.presentation.waterDataDetails.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.confradestech.waterly.R
import com.confradestech.waterly.datasources.states.SelectedWaterDataState
import com.confradestech.waterly.utilites.CommonConstants.AS_DIS
import com.confradestech.waterly.utilites.CommonConstants.As_Tot
import com.confradestech.waterly.utilites.CommonConstants.CN_Tot
import com.confradestech.waterly.utilites.CommonConstants.Cd_Dis
import com.confradestech.waterly.utilites.CommonConstants.Cd_Tot
import com.confradestech.waterly.utilites.CommonConstants.Cr_Dis
import com.confradestech.waterly.utilites.CommonConstants.Cr_Tot
import com.confradestech.waterly.utilites.CommonConstants.Cr_VI
import com.confradestech.waterly.utilites.CommonConstants.DIELDRIN
import com.confradestech.waterly.utilites.CommonConstants.ENDRIN
import com.confradestech.waterly.utilites.CommonConstants.FECALCOLI
import com.confradestech.waterly.utilites.CommonConstants.FECALSTREP
import com.confradestech.waterly.utilites.CommonConstants.Hg_Dis
import com.confradestech.waterly.utilites.CommonConstants.Hg_Tot
import com.confradestech.waterly.utilites.CommonConstants.O2_Dis
import com.confradestech.waterly.utilites.CommonConstants.PH
import com.confradestech.waterly.utilites.CommonConstants.PH_high
import com.confradestech.waterly.utilites.CommonConstants.PH_low
import com.confradestech.waterly.utilites.CommonConstants.Pb_Dis
import com.confradestech.waterly.utilites.CommonConstants.Pb_Tot
import com.confradestech.waterly.utilites.CommonConstants.TURB

@Composable
fun WaterDataDetailsScreen(selectedWaterEntryState: SelectedWaterDataState) {

    val showAlertSection = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {},
        snackbarHost = { /* NO-OP */ },
        floatingActionButton = {/* NO-OP */ },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = MaterialTheme.colorScheme.background,
        content = { _ ->

            AnimatedVisibility(
                modifier = Modifier.fillMaxSize(),
                visible = selectedWaterEntryState.isLoading != false,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )
            }

            if (selectedWaterEntryState.isLoading == false) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(id = R.dimen.spacing_10)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = stringResource(id = R.string.water_details_title).replace(
                            "#value",
                            selectedWaterEntryState.waterEntry?.stationIdentifier ?: ""
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = stringResource(id = R.string.station_identifier).replace(
                            "#name",
                            selectedWaterEntryState.waterEntry?.stationIdentifier ?: ""
                        ),
                        readOnly = true,
                        onValueChange = { /*No-op*/ },
                        label = { /*No-op*/ },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = ""
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
                    if (selectedWaterEntryState.waterSamples?.isEmpty() == true) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = stringResource(id = R.string.no_sample_data),
                            readOnly = true,
                            onValueChange = { /*No-op*/ },
                            label = { /*No-op*/ },
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = ""
                                )
                            },
                        )
                    } else {
                        selectedWaterEntryState.waterSamples?.forEach { waterSample ->
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = stringResource(id = R.string.parameters_code).replace(
                                    "#value",
                                    waterSample.parameter_code
                                ),
                                readOnly = true,
                                onValueChange = { /*No-op*/ },
                                label = { /*No-op*/ },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Home,
                                        contentDescription = ""
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = stringResource(id = R.string.parameters_value).replace(
                                    "#value",
                                    "${waterSample.value}"
                                ),
                                readOnly = true,
                                onValueChange = { /*No-op*/ },
                                label = { /*No-op*/ },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Home,
                                        contentDescription = ""
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))

                            if (selectedWaterEntryState.waterSamplesParameters?.isEmpty() == true) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = stringResource(id = R.string.no_parameters_data),
                                    readOnly = true,
                                    onValueChange = { /*No-op*/ },
                                    label = { /*No-op*/ },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Home,
                                            contentDescription = ""
                                        )
                                    },
                                )
                            } else {
                                selectedWaterEntryState.waterSamplesParameters?.forEach {
                                    OutlinedTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = stringResource(id = R.string.parameters_risk_concern).replace(
                                            "#value",
                                            "${it.human_risk_concern}"
                                        ),
                                        readOnly = true,
                                        onValueChange = { /*No-op*/ },
                                        label = { /*No-op*/ },
                                        leadingIcon = {
                                            Icon(
                                                Icons.Filled.Home,
                                                contentDescription = ""
                                            )
                                        },
                                    )
                                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
                                    OutlinedTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = stringResource(id = R.string.parameters_description).replace(
                                            "#value",
                                            it.parameter_description
                                        ),
                                        readOnly = true,
                                        onValueChange = { /*No-op*/ },
                                        label = { /*No-op*/ },
                                        leadingIcon = {
                                            Icon(
                                                Icons.Filled.Home,
                                                contentDescription = ""
                                            )
                                        },
                                    )

                                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))

                                    val longName: String? =
                                        when (it.parameter_code) {
                                            AS_DIS -> {
                                                stringResource(id = R.string.parameters_As_Dis_Long_Name)
                                            }

                                            As_Tot -> {
                                                stringResource(id = R.string.parameters_As_Tot_Long_Name)
                                            }

                                            Cd_Dis -> {
                                                stringResource(id = R.string.parameters_Cd_Dis_Long_Name)
                                            }

                                            Cd_Tot -> {
                                                stringResource(id = R.string.parameters_Cd_Tot_Long_Name)
                                            }

                                            CN_Tot -> {
                                                stringResource(id = R.string.parameters_CN_Tot_Long_Name)
                                            }

                                            Cr_Dis -> {
                                                stringResource(id = R.string.parameters_Cr_Dis_Long_Name)
                                            }

                                            Cr_Tot -> {
                                                stringResource(id = R.string.parameters_Cr_Tot_Long_Name)
                                            }

                                            Cr_VI -> {
                                                stringResource(id = R.string.parameters_Cr_VI_Long_Name)
                                            }

                                            DIELDRIN -> {
                                                stringResource(id = R.string.parameters_DIELDRIN_Long_Name)
                                            }

                                            ENDRIN -> {
                                                stringResource(id = R.string.parameters_ENDRIN_Long_Name)
                                            }

                                            FECALCOLI -> {
                                                stringResource(id = R.string.parameters_FECALCOLI_Long_Name)
                                            }

                                            FECALSTREP -> {
                                                stringResource(id = R.string.parameters_FECALSTREP_Long_Name)
                                            }

                                            Hg_Dis -> {
                                                stringResource(id = R.string.parameters_Hg_Dis_Long_Name)
                                            }

                                            Hg_Tot -> {
                                                stringResource(id = R.string.parameters_Hg_Tot_Long_Name)
                                            }

                                            O2_Dis -> {
                                                stringResource(id = R.string.parameters_O2_Dis_Long_Name)
                                            }

                                            Pb_Dis -> {
                                                stringResource(id = R.string.parameters_Pb_Dis_Long_Name)
                                            }

                                            Pb_Tot -> {
                                                stringResource(id = R.string.parameters_Pb_Tot_Long_Name)
                                            }

                                            TURB -> {
                                                stringResource(id = R.string.parameters_TURB_Long_Name)
                                            }

                                            PH -> {
                                                if (waterSample.value > PH_high) {
                                                    stringResource(id = R.string.parameters_pH_higher_Long_Name)
                                                } else if (waterSample.value < PH_low) {
                                                    stringResource(id = R.string.parameters_pH_low_Long_Name)
                                                } else {
                                                    ""
                                                }
                                            }

                                            else -> null
                                        }

                                    val healthRecommendation: String? =
                                        when (it.parameter_code) {
                                            AS_DIS -> {
                                                stringResource(id = R.string.parameters_As_Dis_health_Recommendation)
                                            }

                                            As_Tot -> {
                                                stringResource(id = R.string.parameters_As_Tot_health_Recommendation)
                                            }

                                            Cd_Dis -> {
                                                stringResource(id = R.string.parameters_Cd_Dis_health_Recommendation)
                                            }

                                            Cd_Tot -> {
                                                stringResource(id = R.string.parameters_Cd_Tot_health_Recommendation)
                                            }

                                            CN_Tot -> {
                                                stringResource(id = R.string.parameters_CN_Tot_health_Recommendation)
                                            }

                                            Cr_Dis -> {
                                                stringResource(id = R.string.parameters_Cr_Dis_health_Recommendation)
                                            }

                                            Cr_Tot -> {
                                                stringResource(id = R.string.parameters_Cr_Tot_health_Recommendation)
                                            }

                                            Cr_VI -> {
                                                stringResource(id = R.string.parameters_Cr_VI_health_Recommendation)
                                            }

                                            DIELDRIN -> {
                                                stringResource(id = R.string.parameters_DIELDRIN_health_Recommendation)
                                            }

                                            ENDRIN -> {
                                                stringResource(id = R.string.parameters_ENDRIN_health_Recommendation)
                                            }

                                            FECALCOLI -> {
                                                stringResource(id = R.string.parameters_FECALCOLI_health_Recommendation)
                                            }

                                            FECALSTREP -> {
                                                stringResource(id = R.string.parameters_FECALSTREP_health_Recommendation)
                                            }

                                            Hg_Dis -> {
                                                stringResource(id = R.string.parameters_Hg_Dis_health_Recommendation)
                                            }

                                            Hg_Tot -> {
                                                stringResource(id = R.string.parameters_Hg_Tot_health_Recommendation)
                                            }

                                            O2_Dis -> {
                                                stringResource(id = R.string.parameters_O2_Dis_health_Recommendation)
                                            }

                                            Pb_Dis -> {
                                                stringResource(id = R.string.parameters_Pb_Dis_health_Recommendation)
                                            }

                                            Pb_Tot -> {
                                                stringResource(id = R.string.parameters_Pb_Tot_health_Recommendation)
                                            }

                                            TURB -> {
                                                stringResource(id = R.string.parameters_TURB_health_Recommendation)
                                            }

                                            PH -> {
                                                if (waterSample.value > PH_high) {
                                                    stringResource(id = R.string.parameters_pH_higher_health_Recommendation)
                                                } else if (waterSample.value < PH_low) {
                                                    stringResource(id = R.string.parameters_pH_low_health_Recommendation)
                                                } else {
                                                    ""
                                                }
                                            }

                                            else -> null
                                        }

                                    val consequences: String? =
                                        when (it.parameter_code) {
                                            AS_DIS -> {
                                                stringResource(id = R.string.parameters_As_Dis_Consequencs)
                                            }

                                            As_Tot -> {
                                                stringResource(id = R.string.parameters_As_Tot_Consequencs)
                                            }

                                            Cd_Dis -> {
                                                stringResource(id = R.string.parameters_Cd_Dis_Consequencs)
                                            }

                                            Cd_Tot -> {
                                                stringResource(id = R.string.parameters_Cd_Tot_Consequencs)
                                            }

                                            CN_Tot -> {
                                                stringResource(id = R.string.parameters_CN_Tot_Consequencs)
                                            }

                                            Cr_Dis -> {
                                                stringResource(id = R.string.parameters_Cr_Dis_Consequencs)
                                            }

                                            Cr_Tot -> {
                                                stringResource(id = R.string.parameters_Cr_Tot_Consequencs)
                                            }

                                            Cr_VI -> {
                                                stringResource(id = R.string.parameters_Cr_VI_Consequencs)
                                            }

                                            DIELDRIN -> {
                                                stringResource(id = R.string.parameters_DIELDRIN_Consequencs)
                                            }

                                            ENDRIN -> {
                                                stringResource(id = R.string.parameters_ENDRIN_Consequencs)
                                            }

                                            FECALCOLI -> {
                                                stringResource(id = R.string.parameters_FECALCOLI_Consequencs)
                                            }

                                            FECALSTREP -> {
                                                stringResource(id = R.string.parameters_FECALSTREP_Consequencs)
                                            }

                                            Hg_Dis -> {
                                                stringResource(id = R.string.parameters_Hg_Dis_Consequencs)
                                            }

                                            Hg_Tot -> {
                                                stringResource(id = R.string.parameters_Hg_Tot_Consequencs)
                                            }

                                            O2_Dis -> {
                                                stringResource(id = R.string.parameters_O2_Dis_Consequencs)
                                            }

                                            Pb_Dis -> {
                                                stringResource(id = R.string.parameters_Pb_Dis_Consequencs)
                                            }

                                            Pb_Tot -> {
                                                stringResource(id = R.string.parameters_Pb_Tot_Consequencs)
                                            }

                                            TURB -> {
                                                stringResource(id = R.string.parameters_TURB_Consequencs)
                                            }

                                            PH -> {
                                                if (waterSample.value > PH_high) {
                                                    stringResource(id = R.string.parameters_pH_higher_Consequencs)
                                                } else if (waterSample.value < PH_low) {
                                                    stringResource(id = R.string.parameters_pH_low_Consequencs)
                                                } else {
                                                    ""
                                                }
                                            }

                                            else -> null
                                        }

                                    val treatmentRecommendation: String? =
                                        when (it.parameter_code) {
                                            AS_DIS -> {
                                                stringResource(id = R.string.parameters_As_Dis_treatment_Recommendation)
                                            }

                                            As_Tot -> {
                                                stringResource(id = R.string.parameters_As_Tot_treatment_Recommendation)
                                            }

                                            Cd_Dis -> {
                                                stringResource(id = R.string.parameters_Cd_Dis_treatment_Recommendation)
                                            }

                                            Cd_Tot -> {
                                                stringResource(id = R.string.parameters_Cd_Tot_treatment_Recommendation)
                                            }

                                            CN_Tot -> {
                                                stringResource(id = R.string.parameters_CN_Tot_treatment_Recommendation)
                                            }

                                            Cr_Dis -> {
                                                stringResource(id = R.string.parameters_Cr_Dis_treatment_Recommendation)
                                            }

                                            Cr_Tot -> {
                                                stringResource(id = R.string.parameters_Cr_Tot_treatment_Recommendation)
                                            }

                                            Cr_VI -> {
                                                stringResource(id = R.string.parameters_Cr_VI_treatment_Recommendation)
                                            }

                                            DIELDRIN -> {
                                                stringResource(id = R.string.parameters_DIELDRIN_treatment_Recommendation)
                                            }

                                            ENDRIN -> {
                                                stringResource(id = R.string.parameters_ENDRIN_treatment_Recommendation)
                                            }

                                            FECALCOLI -> {
                                                stringResource(id = R.string.parameters_FECALCOLI_treatment_Recommendation)
                                            }

                                            FECALSTREP -> {
                                                stringResource(id = R.string.parameters_FECALSTREP_treatment_Recommendation)
                                            }

                                            Hg_Dis -> {
                                                stringResource(id = R.string.parameters_Hg_Dis_treatment_Recommendation)
                                            }

                                            Hg_Tot -> {
                                                stringResource(id = R.string.parameters_Hg_Tot_treatment_Recommendation)
                                            }

                                            O2_Dis -> {
                                                stringResource(id = R.string.parameters_O2_Dis_treatment_Recommendation)
                                            }

                                            Pb_Dis -> {
                                                stringResource(id = R.string.parameters_Pb_Dis_treatment_Recommendation)
                                            }

                                            Pb_Tot -> {
                                                stringResource(id = R.string.parameters_Pb_Tot_treatment_Recommendation)
                                            }

                                            TURB -> {
                                                stringResource(id = R.string.parameters_TURB_treatment_Recommendation)
                                            }

                                            PH -> {
                                                if (waterSample.value > PH_high) {
                                                    stringResource(id = R.string.parameters_pH_higher_treatment_Recommendation)
                                                } else if (waterSample.value < PH_low) {
                                                    stringResource(id = R.string.parameters_pH_low_treatment_Recommendation)
                                                } else {
                                                    ""
                                                }
                                            }

                                            else -> null
                                        }

                                    showAlertSection.value =
                                        !longName.isNullOrEmpty() ||
                                                !healthRecommendation.isNullOrEmpty() ||
                                                !consequences.isNullOrEmpty() ||
                                                !treatmentRecommendation.isNullOrEmpty()


                                    if (showAlertSection.value) {
                                        Surface(
                                            modifier = Modifier
                                                .wrapContentWidth()
                                                .wrapContentHeight(),
                                            shape = MaterialTheme.shapes.small,
                                            color = MaterialTheme.colorScheme.onTertiary,
                                            tonalElevation = AlertDialogDefaults.TonalElevation
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(dimensionResource(id = R.dimen.spacing_10))
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.Start,
                                                ) {
                                                    Icon(
                                                        Icons.Rounded.Info,
                                                        contentDescription = ""
                                                    )

                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(
                                                                dimensionResource(
                                                                    id = R.dimen.spacing_10
                                                                )
                                                            )
                                                    ) {

                                                        longName?.let {
                                                            if (it.isNotEmpty()) {
                                                                OutlinedTextField(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    value = it,
                                                                    readOnly = true,
                                                                    onValueChange = { /*No-op*/ },
                                                                    label = { /*No-op*/ },
                                                                )
                                                                Spacer(
                                                                    modifier = Modifier.height(
                                                                        dimensionResource(id = R.dimen.spacing_5)
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        healthRecommendation?.let {
                                                            if (it.isNotEmpty()) {
                                                                OutlinedTextField(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    value = it,
                                                                    readOnly = true,
                                                                    onValueChange = { /*No-op*/ },
                                                                    label = { /*No-op*/ },
                                                                )
                                                                Spacer(
                                                                    modifier = Modifier.height(
                                                                        dimensionResource(id = R.dimen.spacing_5)
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        consequences?.let {
                                                            if (it.isNotEmpty()) {

                                                                OutlinedTextField(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    value = it,
                                                                    readOnly = true,
                                                                    onValueChange = { /*No-op*/ },
                                                                    label = { /*No-op*/ },
                                                                )
                                                                Spacer(
                                                                    modifier = Modifier.height(
                                                                        dimensionResource(id = R.dimen.spacing_5)
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        treatmentRecommendation?.let {
                                                            if (it.isNotEmpty()) {
                                                                OutlinedTextField(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    value = it,
                                                                    readOnly = true,
                                                                    onValueChange = { /*No-op*/ },
                                                                    label = { /*No-op*/ },
                                                                )
                                                                Spacer(
                                                                    modifier = Modifier.height(
                                                                        dimensionResource(id = R.dimen.spacing_5)
                                                                    )
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    )
}
