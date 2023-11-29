package com.pardo.crackdetect.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

internal fun dimen() = Dimen()
internal fun spacing() = Spacing()
internal fun cornerRadius() = CornerRadius()

class Dimen {
    val BackgroundPadding = PaddingValues(vertical = Spacing().L, horizontal = Spacing().M)
    val OutlinedButtonPadding = PaddingValues(0.dp, 14.dp)
    val TextButtonPadding = PaddingValues(16.dp, 14.dp)
    val ContainedButtonPadding = PaddingValues(0.dp, 14.dp)
    val ButtonIcon = 48.dp
    val Icon = 24.dp
    val ImageCircularPadding = PaddingValues(top = Spacing().XL, bottom = Spacing().ZERO, start = Spacing().M, end = Spacing().M)
    val ImageCircularSize = 350.dp
}

class Spacing {
    val ZERO = 0.dp
    val XS = 4.dp
    val S = 8.dp
    val M = 16.dp
    val L = 24.dp
    val XL = 32.dp
}

class CornerRadius {
    val Button = 8.dp
}
