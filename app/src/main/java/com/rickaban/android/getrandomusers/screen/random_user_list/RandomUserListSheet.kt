package com.rickaban.android.getrandomusers.screen.random_user_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.rickaban.android.getrandomusers.app.ui.theme.ThemeWrapper
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.screen.random_user_list.component.SearchBar
import com.rickaban.android.getrandomusers.screen.random_user_list.component.SearchResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUserListSheet(
    state: RandomUserListState,
    swipeRefreshState: SwipeRefreshState,
    lazyListState: LazyListState,
    scrollBehavior: TopAppBarScrollBehavior,
    action: (RandomUserListAction) -> Unit
) {
    ThemeWrapper {
        Scaffold(
            topBar = {
                TopBar(state, scrollBehavior, action)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchBar(state, action)
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { action(RandomUserListAction.SwipeRefresh) }
                ) {
                    Box(Modifier.fillMaxSize()) {
                        Column {
                            state.error?.let { _ ->
                                Text(
                                    text = stringResource(id = R.string.something_went_wrong),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            SearchResult(state, lazyListState, scrollBehavior, action)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    state: RandomUserListState,
    scrollBehavior: TopAppBarScrollBehavior,
    action: (RandomUserListAction) -> Unit
) {
    TopAppBar(
        title = {
        },
        actions = {
            IconButton(
                onClick = {
                    action(RandomUserListAction.NavToSettings)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "setting"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}