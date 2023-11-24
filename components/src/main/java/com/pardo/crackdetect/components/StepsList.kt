package com.pardo.crackdetect.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pardo.crackdetect.theme.CrackDetectTheme

@Composable
fun StepsList(
    modifier: Modifier = Modifier,
    items: List<StepItem> = listOf()
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
        content = {
            itemsIndexed(items) { index, item ->
                Column {
                    Titles.SimpleTitle(text = "${index + 1}. ${item.title} ")
                    Titles.SimpleDescription(text = item.description)
                    if (index <= items.size -1 ) {
                        Spacer(modifier = Modifier.height(CrackDetectTheme.Spacing.M))
                    }
                }
            }
        }
    )
}

data class StepItem(
    val title: String,
    val description: String
)