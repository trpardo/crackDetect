package com.pardo.crackdetect.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.pardo.crackdetect.theme.CrackDetectTheme

object Titles {

    @Composable
    fun ExtraTitle(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = CrackDetectTheme.Typography.headlineLarge,
            textAlign = textAlign
        )
    }

    @Composable
    fun ExtraSubtitle(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = CrackDetectTheme.Typography.displayLarge,
            textAlign = textAlign
        )
    }

    @Composable
    fun SimpleTitle(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = CrackDetectTheme.Typography.titleMedium,
            textAlign = textAlign
        )
    }

    @Composable
    fun SimpleDescription(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = CrackDetectTheme.Typography.bodyMedium,
            textAlign = textAlign
        )
    }

    @Composable
    fun Label(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = CrackDetectTheme.Typography.labelMedium,
            textAlign = textAlign
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "TitlesPreviewLight"
)
@Composable
fun TitlesPreviewLight() {
    TitlesPreview()
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "TitlesPreviewDark"
)
@Composable
fun TitlesPreviewDark() {
    TitlesPreview()
}

@Composable
fun TitlesPreview() {
    CrackDetectTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Titles.ExtraTitle(text = "Extra title")
            Titles.ExtraSubtitle(text = "Extra subtitle")
            Titles.SimpleTitle(text = "Title")
            Titles.SimpleDescription(text = "Description")
            Titles.Label(text = "label")
        }
    }
}
