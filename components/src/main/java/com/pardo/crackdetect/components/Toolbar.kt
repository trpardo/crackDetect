package com.pardo.crackdetect.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.pardo.crackdetect.theme.CrackDetectTheme

object Toolbar {

    @Composable
    fun Simple(
        title: String
    ) {
        BaseToolbar(
            title = title,
            description = title
        )
    }

    @Composable
    fun Nav(
        title: String,
        onClick: () -> Unit
    ) {
        BaseToolbar(
            title = title,
            imageVector = Icons.Default.KeyboardArrowLeft,
            description = "Back button",
            onClick = onClick
        )
    }

    @Composable
    fun Modal(
        title: String,
        onClick: () -> Unit
    ) {
        BaseToolbar(
            title = title,
            imageVector = Icons.Default.Close,
            description = "Close button",
            onClick = onClick
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun BaseToolbar(
        title: String,
        imageVector: ImageVector? = null,
        description: String,
        onClick: () -> Unit = {}
    ) {
        CenterAlignedTopAppBar(
            title = { Titles.SimpleTitle(text = title) },
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                imageVector?.let {
                    IconButton(onClick = onClick ) {
                        Icon(imageVector = imageVector, contentDescription = description)
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            windowInsets = TopAppBarDefaults.windowInsets
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "ToolbarPreviewLight"
)
@Composable
fun ToolbarPreviewLight() {
    ToolbarPreview()
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "ToolbarPreviewDark"
)
@Composable
fun ToolbarPreviewDark() {
    ToolbarPreview()
}

@Composable
fun ToolbarPreview() {
    CrackDetectTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Toolbar.Simple(title = "Regular toolbar")
            Toolbar.Nav(title = "Nav toolbar", onClick = {})
            Toolbar.Modal(title = "Modal toolbar", onClick = {})
        }
    }
}