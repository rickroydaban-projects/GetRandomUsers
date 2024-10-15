package com.rickaban.android.getrandomusers.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AlignedRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable RowScope.()->Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    selection: String?,
    onSelected: (String)->Unit,
    expandedState: MutableState<Boolean>,
    selections: Map<String, String>,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedState.value,
            onExpandedChange = {
                expandedState.value = !expandedState.value
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selections[selection] ?: "",
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandedState.value,
                onDismissRequest = { expandedState.value = false },
                modifier = Modifier.fillMaxWidth(0.8F)
            ) {
                selections.forEach { selection ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selection.value,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            onSelected(selection.key)
                            expandedState.value = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}        