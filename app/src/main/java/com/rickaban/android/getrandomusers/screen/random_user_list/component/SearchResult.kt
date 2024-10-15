package com.rickaban.android.getrandomusers.screen.random_user_list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.app.ui.AlignedRow
import com.rickaban.android.getrandomusers.screen.random_user_list.RandomUserListAction
import com.rickaban.android.getrandomusers.screen.random_user_list.RandomUserListState
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun SearchResult(
    state: RandomUserListState,
    lazyListState: LazyListState,
    scrollBehavior: TopAppBarScrollBehavior,
    action: (RandomUserListAction) -> Unit
) {
    state.randomUserList?.let { list ->
        if(list.isEmpty()) {
            EmptyResult(state, action)
        } else {
            val groupedStrings = state.randomUserList.sortedBy { it.firstName }.groupBy { it.firstName.first().uppercase() }

            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                groupedStrings.forEach { (letter, items) ->
                    stickyHeader {
                        Header(letter)
                    }

                    items(items) { randomUser ->
                        RandomUserRow(
                            randomUser = randomUser,
                            state = state,
                            action = action
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(
    letter: String
) {
    Text(
        text = letter,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = 34.dp,
                top = 17.dp,
                bottom = 9.dp
            ),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun RandomUserRow(
    randomUser: RandomUser,
    state: RandomUserListState,
    action: (RandomUserListAction) -> Unit
) {
    AlignedRow(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                action(RandomUserListAction.ClickRandomUser(randomUser))
            }
            .padding(
                horizontal = 17.dp,
                vertical = 7.dp
            )
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            val painter = rememberAsyncImagePainter(
                model = randomUser.profilePictureUrl,
                onError = {
                    it.result.throwable.printStackTrace()
                }
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(7.dp))
        Column {
            Text(
                text = "${randomUser.firstName} ${randomUser.lastName}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = randomUser.address,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(0.6F)
            )
        }
    }
}

@Composable
private fun EmptyResult(
    state: RandomUserListState,
    action: (RandomUserListAction) -> Unit
) {
    Text(
        text = stringResource(id = R.string.no_user_found),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(17.dp)
    )
}