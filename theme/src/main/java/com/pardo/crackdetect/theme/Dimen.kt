package com.pardo.crackdetect.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

internal fun dimen() = Dimen()
internal fun spacing() = Spacing()
internal fun cornerRadius() = CornerRadius()

class Dimen {
    val NoPadding = PaddingValues(0.dp)
    val BackgroundPadding = PaddingValues(vertical = Spacing().L, horizontal = Spacing().M)
    val OutlinedStroke = 1.dp
    val OutlinedButtonPadding = PaddingValues(0.dp, 14.dp)
    val TextButtonPadding = PaddingValues(16.dp, 14.dp)
    val ContainedButtonPadding = PaddingValues(0.dp, 14.dp)
    val ToolbarHeight = 56.dp
    val ButtonIcon = 48.dp
    val Icon = 24.dp
    val TextFieldAreaHeight = 96.dp
    val Divider = 1.dp
    val WelcomeHeaderPadding = PaddingValues(top = Spacing().XL, bottom = Spacing().ZERO, start = Spacing().M, end = Spacing().M)
}

class Spacing {
    val ZERO = 0.dp
    val XS = 4.dp
    val S = 8.dp
    val M = 16.dp
    val L = 24.dp
    val XL = 32.dp
    val XXL = 40.dp
}

class CornerRadius {
    val NoRadius = 0.dp
    val TextField = 8.dp
    val Card = 8.dp
    val Section = 16.dp
    val Button = 8.dp
    val Divider = 2.dp
    val Icon = 12.dp
    val SnackBar = 4.dp
    val Banner = 16.dp
}
