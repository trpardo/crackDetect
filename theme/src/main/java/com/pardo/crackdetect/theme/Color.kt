package com.pardo.crackdetect.theme

import androidx.compose.ui.graphics.Color

val StandardWhite = Color(0xFFFFFFFF)
val StandardBlack = Color(0xFF000000)
val StandardGrey20 = Color(0xFFDCE5DD)
val StandardGrey40 = Color(0xFF707972)
val StandardGrey60 = Color(0xFF404943)
val StandardGrey70 = Color(0xFF2E312F)
val StandardGrey80 = Color(0xFF191C1A)
val Transparent = Color(0x00FFFFFF)

val PrimaryGreen = Color(0xFF006C48)
val PrimaryGreenVariant = Color(0xFF8DF7C1)
val PrimaryGreenDark = Color(0xFF002113)
val SecondaryGreen = Color(0xFF006A64)
val SecondaryGreenVariant = Color(0xFF71F7ED)
val SecondaryGreenDark = Color(0xFF00201E)
val TertiaryGreen = Color(0xFF3C6472)
val TertiaryGreenVariant = Color(0xFFC0E9FA)
val TertiaryGreenDark = Color(0xFF001F28)

val StandardRed = Color(0xFFBA1A1A)
val LightRed = Color(0xFFFFDAD6)
val DarkRed = Color(0xFF410002)

internal fun lightColorScheme() = LightColorScheme
internal fun darkColorScheme() = DarkColorScheme

private val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = StandardWhite,
    primaryContainer = PrimaryGreenVariant,
    onPrimaryContainer = PrimaryGreenDark,
    secondary = SecondaryGreen,
    onSecondary = StandardWhite,
    secondaryContainer = SecondaryGreenVariant,
    onSecondaryContainer = SecondaryGreenDark,
    tertiary = TertiaryGreen,
    onTertiary = StandardWhite,
    tertiaryContainer = TertiaryGreenVariant,
    onTertiaryContainer = TertiaryGreenDark,
    error = StandardRed,
    errorContainer = LightRed,
    onError = StandardWhite,
    onErrorContainer = DarkRed,
    background = StandardGrey20,
    onBackground = StandardGrey80,
    surface = StandardGrey20,
    onSurface = StandardGrey80,
    surfaceVariant = StandardGrey20,
    onSurfaceVariant = StandardGrey60,
    outline = PrimaryGreen,
    inverseOnSurface = StandardGrey20,
    inverseSurface = StandardGrey70,
    inversePrimary = PrimaryGreenVariant,
    surfaceTint = PrimaryGreen,
    outlineVariant = StandardGrey20,
    scrim = StandardBlack
)

private val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = Color(0xFF71DBA7),
    onPrimary = Color(0xFF003823),
    primaryContainer = Color(0xFF005235),
    onPrimaryContainer = Color(0xFF8DF7C1),
    secondary = Color(0xFF4FDBD0),
    onSecondary = Color(0xFF003734),
    secondaryContainer = Color(0xFF00504B),
    onSecondaryContainer = Color(0xFF71F7ED),
    tertiary = Color(0xFFA4CDDD),
    onTertiary = Color(0xFF053542),
    tertiaryContainer = Color(0xFF234C5A),
    onTertiaryContainer = Color(0xFFC0E9FA),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF191C1A),
    onBackground = Color(0xFFE1E3DF),
    surface = Color(0xFF191C1A),
    onSurface = Color(0xFFE1E3DF),
    surfaceVariant = Color(0xFF404943),
    onSurfaceVariant = Color(0xFFC0C9C1),
    outline = Color(0xFF8A938C),
    inverseOnSurface = Color(0xFF191C1A),
    inverseSurface = Color(0xFFE1E3DF),
    inversePrimary = Color(0xFF006C48),
    surfaceTint = Color(0xFF71DBA7),
    outlineVariant = Color(0xFF404943),
    scrim = StandardBlack
)