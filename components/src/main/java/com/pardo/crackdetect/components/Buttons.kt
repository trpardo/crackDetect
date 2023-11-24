package com.pardo.crackdetect.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import com.pardo.crackdetect.theme.CrackDetectTheme

object Buttons {
    @Composable
    fun Filled(
        modifier: Modifier = Modifier,
        text: String,
        onClick: (() -> Unit),
        enabled: Boolean = true
    ) {
        FilledButton(
            modifier = modifier,
            text = text,
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(10),
        )
    }

    @Composable
    fun Outline(
        modifier: Modifier = Modifier,
        text: String,
        onClick: (() -> Unit),
        enabled: Boolean = true
    ) {
        OutlinedButton(
            text = text,
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(CrackDetectTheme.CornerRadius.Button),
            modifier = modifier
        )
    }

    @Composable
    fun Text(
        modifier: Modifier = Modifier,
        text: String,
        onClick: (() -> Unit),
        enabled: Boolean = true
    ) {
        TextButton(
            text = text,
            onClick = onClick,
            enabled = enabled,
            modifier = modifier
        )
    }

    @Composable
    fun Icon(
        @DrawableRes iconResId: Int,
        description: String,
        enabled: Boolean = true,
        onClick: () -> Unit = {}
    ) {
        IconButton(
            modifier = Modifier.size(size = CrackDetectTheme.Dimen.ButtonIcon),
            onClick = onClick,
            content = {
                androidx.compose.material3.Icon(
                    modifier = Modifier.size(CrackDetectTheme.Dimen.Icon),
                    painter = painterResource(id = iconResId),
                    contentDescription = description,
                )
            },
            enabled = enabled
        )
    }

    @Composable
    fun IconText(
        modifier: Modifier = Modifier,
        text: String,
        @DrawableRes iconResId: Int,
        iconDescription: String? = null,
        onClick: (() -> Unit),
        enabled: Boolean = true
    ) {
        Row(
            modifier = modifier.clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.S)
        ) {
            androidx.compose.material3.Icon(
                modifier = Modifier.size(CrackDetectTheme.Dimen.Icon),
                painter = painterResource(id = iconResId),
                contentDescription = iconDescription,
                tint = iconTextContentColor(enabled = enabled)
            )
            androidx.compose.material3.Text(
                text = text,
                style = CrackDetectTheme.Typography.bodySmall,
                color = iconTextContentColor(enabled = enabled)
            )
        }
    }

    @Composable
    fun FloatingIcon(
        @DrawableRes iconResId: Int,
        iconDescription: String? = null,
        onClick: () -> Unit,
        enabled: Boolean = true
    ) {
        FloatingActionButton(onClick = onClick) {
            androidx.compose.material3.Icon(
                modifier = Modifier.size(CrackDetectTheme.Dimen.Icon),
                painter = painterResource(id = iconResId),
                contentDescription = iconDescription,
                tint = iconTextContentColor(enabled = enabled)
            )
        }
    }

    @Composable
    private fun FilledButton(
        modifier: Modifier = Modifier,
        text: String,
        onClick: () -> Unit,
        enabled: Boolean = true,
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        shape: RoundedCornerShape
    ) {
        val focusManager = LocalFocusManager.current
        Button(
            onClick = addClearFocus(onClick, focusManager),
            modifier = modifier,
            enabled = enabled,
            elevation = elevation,
            shape = shape,
            contentPadding = CrackDetectTheme.Dimen.ContainedButtonPadding
        ) {
            androidx.compose.material3.Text(
                text = text,
                style = CrackDetectTheme.Typography.titleMedium
            )
        }
    }

    @Composable
    private fun OutlinedButton(
        modifier: Modifier = Modifier,
        text: String,
        onClick: () -> Unit,
        enabled: Boolean = true,
        elevation: ButtonElevation? = null,
        shape: RoundedCornerShape
    ) {
        val focusManager = LocalFocusManager.current
        androidx.compose.material3.OutlinedButton(
            onClick = addClearFocus(onClick, focusManager),
            modifier = modifier,
            enabled = enabled,
            elevation = elevation,
            shape = shape,
            contentPadding = CrackDetectTheme.Dimen.OutlinedButtonPadding
        ) {
            androidx.compose.material3.Text(
                text = text,
                style = CrackDetectTheme.Typography.titleMedium
            )
        }
    }

    @Composable
    private fun TextButton(
        modifier: Modifier = Modifier,
        text: String,
        textStyle: TextStyle = CrackDetectTheme.Typography.titleMedium,
        contentPadding: PaddingValues = CrackDetectTheme.Dimen.TextButtonPadding,
        onClick: () -> Unit,
        enabled: Boolean = true,
        elevation: ButtonElevation? = null,
        content: @Composable RowScope.() -> Unit = {}
    ) {
        val focusManager = LocalFocusManager.current
        androidx.compose.material3.TextButton(
            modifier = modifier,
            onClick = addClearFocus(onClick, focusManager),
            enabled = enabled,
            elevation = elevation,
            contentPadding = contentPadding,
            content = {
                content()
                androidx.compose.material3.Text(
                    text = text,
                    style = textStyle
                )
            }
        )
    }

    private fun addClearFocus(onClick: () -> Unit, focusManager: FocusManager): () -> Unit = {
        onClick.invoke()
        focusManager.clearFocus()
    }

    @Composable
    private fun iconTextContentColor(enabled: Boolean) =
        if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
}