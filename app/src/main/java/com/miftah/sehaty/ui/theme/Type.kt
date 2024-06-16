package com.miftah.sehaty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.R

// Set of Material typography styles to start with

val Roboto = FontFamily(
    listOf(
        Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
        Font(resId = R.font.roboto_regular, weight = FontWeight.Normal),
        Font(resId = R.font.roboto_bold, weight = FontWeight.ExtraBold),
    )
)


val MediumTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )

)