package com.pardo.crackdetect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pardo.crackdetect.theme.CrackDetectTheme

@Composable
fun DescriptionRow(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.XS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Titles.ExtraSubtitle(text = title)
        Titles.SimpleTitle(text = description)
    }
}
