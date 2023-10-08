package com.confradestech.waterly.utilites.components.commom

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Night mode - standard screens",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class NightModeStandardScreens

@Preview(
    name = "Light mode - standard screens",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class LightModeStandardScreens

@Preview(
    name = "Night mode - big screens portrait",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_c,orientation=portrait"
)
annotation class NightModeBigScreensPortrait

@Preview(
    name = "Light mode - big screens portrait",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_c,orientation=portrait"

)
annotation class LightModeBigScreensPortrait

@Preview(
    name = "Night mode - big screens landscape",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_c,orientation=landscape",
)
annotation class NightModeBigScreensLandscape

@Preview(
    name = "Light mode - big screens landscape",
    group = "BerkanIt",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_c,orientation=landscape",
)
annotation class LightModeBigScreensLandscape
