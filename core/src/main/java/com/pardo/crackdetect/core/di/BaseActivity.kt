package com.pardo.crackdetect.core.di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.fragment.app.FragmentActivity
import com.pardo.crackdetect.theme.CrackDetectTheme

abstract class BaseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

fun BaseActivity.content(
    activity: ComponentActivity,
    parent: CompositionContext? = null,
    content: @Composable () -> Unit
) {
    activity.setContent(parent = parent) {
        CrackDetectTheme {
            content()
        }
    }
}