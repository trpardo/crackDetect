package com.pardo.crackdetect.core.nav

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher

class NavigationUtils(private val sourceActivity: ComponentActivity) {
    fun startActivity(
        activityClass: Class<*>,
        extras: Bundle? = null,
        resultLauncher: ActivityResultLauncher<Intent>?
    ) {
        val intent = Intent(sourceActivity, activityClass)
        this.putExtras(intent, extras)
        this.startActivity(intent, resultLauncher)
    }

    fun startActivity(action: String, resultLauncher: ActivityResultLauncher<Intent>?) {
        val intent = Intent().apply {
            this.action = action
        }
        this.startActivity(intent, resultLauncher)
    }

    fun startActivityClearingTask(
        activityClass: Class<*>,
        extras: Bundle? = null,
        resultLauncher: ActivityResultLauncher<Intent>?
    ) {
        val intent = Intent(sourceActivity, activityClass)
        this.putExtras(intent, extras)
        this.startActivityClearingTask(intent, resultLauncher)
    }

    fun startActivityClearingTask(action: String, resultLauncher: ActivityResultLauncher<Intent>?) {
        val intent = Intent().apply {
            this.action = action
        }
        this.startActivityClearingTask(intent, resultLauncher)
    }

    private fun startActivity(intent: Intent, resultLauncher: ActivityResultLauncher<Intent>?) {
        resultLauncher?.let {
            resultLauncher.launch(intent)
        } ?: kotlin.run {
            sourceActivity.startActivity(intent)
        }
    }

    private fun startActivityClearingTask(
        intent: Intent,
        resultLauncher: ActivityResultLauncher<Intent>?
    ) {
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        resultLauncher?.let {
            resultLauncher.launch(intent)
        } ?: kotlin.run {
            sourceActivity.startActivity(intent)
        }
        sourceActivity.finishAndRemoveTask()
    }

    private fun putExtras(intent: Intent, extras: Bundle?): Intent {
        extras?.let {
            intent.putExtras(it)
        }
        return intent
    }
}