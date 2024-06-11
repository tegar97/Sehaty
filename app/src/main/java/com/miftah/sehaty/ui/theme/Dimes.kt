package com.miftah.sehaty.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val buttonHeight: Dp = 60.dp,
    val logoSize: Dp = 24.dp
)

val MediumDimens = Dimens(
    small1 = 8.dp,
    small2 = 16.dp,
    medium1 = 24.dp,
    medium2 = 32.dp,
    medium3 = 48.dp,
    large = 64.dp
)