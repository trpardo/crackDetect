package com.pardo.crackdetect.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.pardo.crackdetect.theme.CrackDetectTheme

@Composable
fun Background(content: @Composable () -> Unit) {
    val localFocusManager = LocalFocusManager.current
    Surface(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures { localFocusManager.clearFocus() }
        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = CrackDetectTheme.Dimen.BackgroundPadding,
    onBack: (() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Background {
        Scaffold(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackBar,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .scrollable(rememberScrollState(), Orientation.Vertical)
                ) {
                    content(paddingValues)
                }
            }
        )
    }
}
