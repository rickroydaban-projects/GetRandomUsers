package com.rickaban.android.getrandomusers.screen.random_user_list.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.app.ui.AlignedRow
import com.rickaban.android.getrandomusers.screen.random_user_list.RandomUserListAction
import com.rickaban.android.getrandomusers.screen.random_user_list.RandomUserListState

@Composable
internal fun SearchBar(
    state: RandomUserListState,
    action: (RandomUserListAction) -> Unit
) {
    Spacer(modifier = Modifier.height(12.dp))
    AlignedRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        TextField(
            value = state.query,
            onValueChange = {
                action(RandomUserListAction.UpdateQuery(it))
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.Gray.copy(0.5F),
                    contentDescription = "search"
                )
            },
            trailingIcon = {
                if(state.query.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            action(RandomUserListAction.ClearQuery)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "cancel"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    action(RandomUserListAction.DismissKeyboard)
                }
            ),
            supportingText = {
                if(state.query.isEmpty()) {
                    Text(text = stringResource(id = R.string.to_begin_input_number_of_users))
                }
            },
            modifier = Modifier.weight(1F),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}