package com.pardo.crackdetect.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pardo.crackdetect.components.Buttons
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.components.StepItem
import com.pardo.crackdetect.components.StepsList
import com.pardo.crackdetect.components.TitleSubtitleHeader
import com.pardo.crackdetect.nav.DashboardNavigator
import com.pardo.crackdetect.theme.CrackDetectTheme
import com.pardo.crackdetect.theme.R

@Composable
fun WelcomeScreen(
    navigator: DashboardNavigator? = null
) {
    ScreenContainer(
        topBar = { WelcomeHeader() },
        content = { paddingValues ->
            WelcomeContent(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CrackDetectTheme.Spacing.M)
            ) {
                Buttons.Filled(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.app_button_start),
                    onClick = { navigator?.startAnalysis() }
                )
            }
        }
    )
}

@Composable
private fun WelcomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CrackDetectTheme.Dimen.ImageCircularPadding),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(id = R.drawable.img_home),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun WelcomeContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.XL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleSubtitleHeader(
            title = stringResource(id = R.string.app_greeting_title),
            subtitle = stringResource(id = R.string.app_greeting_subtitle)
        )
        StepsList(
            modifier = Modifier.fillMaxWidth(),
            items = listOf(
                StepItem(
                    title = stringResource(id = R.string.app_welcome_step1_title),
                    description = stringResource(id = R.string.app_welcome_step1_description)
                ),
                StepItem(
                    title = stringResource(id = R.string.app_welcome_step2_title),
                    description = stringResource(id = R.string.app_welcome_step2_description)
                ),
                StepItem(
                    title = stringResource(id = R.string.app_welcome_step3_title),
                    description = stringResource(id = R.string.app_welcome_step3_description)
                )
            )
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "WelcomeScreenLight"
)
@Composable
fun WelcomeScreenLight() {
    WelcomeScreenPreview()
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "WelcomeScreenDark"
)
@Composable
fun WelcomeScreenDark() {
    WelcomeScreenPreview()
}

@Composable
private fun WelcomeScreenPreview() {
    CrackDetectTheme {
        WelcomeScreen()
    }
}
