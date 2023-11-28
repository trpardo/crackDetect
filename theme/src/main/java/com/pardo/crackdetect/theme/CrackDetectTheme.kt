package com.pardo.crackdetect.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun CrackDetectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localTypographySystem provides typography(),
        localDimenSystem provides dimen(),
        localSpacingSystem provides spacing(),
        localCornerRadiusSystem provides cornerRadius()
    ) {
        val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> darkColorScheme()
            else -> lightColorScheme()
        }
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            }
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = CrackDetectTheme.Typography,
            content = content
        )
    }
}

private val localTypographySystem = staticCompositionLocalOf<Typography> { error("Typography error") }
private val localDimenSystem = staticCompositionLocalOf<Dimen> { error("Dimen error") }
private val localSpacingSystem = staticCompositionLocalOf<Spacing> { error("Spacing error") }
private val localCornerRadiusSystem = staticCompositionLocalOf<CornerRadius> { error("Corner radius error") }

object CrackDetectTheme {
    val Typography @Composable get() = localTypographySystem.current
    val Dimen @Composable get() = localDimenSystem.current
    val Spacing @Composable get() = localSpacingSystem.current
    val CornerRadius @Composable get() = localCornerRadiusSystem.current
}
