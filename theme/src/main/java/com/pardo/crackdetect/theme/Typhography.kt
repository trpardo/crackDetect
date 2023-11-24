package com.pardo.crackdetect.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal fun typography() = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.1.sp,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.1.sp,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.1.sp,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    )
)