package com.example.bostatask.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.bostatask.ui.components.GameItem
import com.example.bostatask.ui.screens.list.model.GameUiModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    viewModel: ListViewModel = koinViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val slugs = remember { listOf("action", "adventure", "shooter", "role-playing-games-rpg") }

    val pagerState = rememberPagerState(pageCount = { slugs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search Games") },
            shape = RoundedCornerShape(12.dp)
        )

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 16.dp,
            divider = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            slugs.forEachIndexed { index, slug ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = slug.replaceFirstChar { it.uppercase() },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val slug = slugs[page]

            val games = remember(slug) {
                viewModel.getGamesPagingData(slug)
            }.collectAsLazyPagingItems()

            GameList(
                games = games,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }
}

@Composable
fun GameList(
    games: LazyPagingItems<GameUiModel>,
    onNavigateToDetails: (Int) -> Unit
) {

    val refreshState = games.loadState.refresh
    val appendState = games.loadState.append

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (refreshState is LoadState.Loading && games.itemCount == 0) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (refreshState is LoadState.Error && games.itemCount == 0) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Error: ${refreshState.error.localizedMessage}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { games.retry() }) {
                    Text("Retry")
                }
            }
        } else if (games.itemCount == 0 && refreshState is LoadState.NotLoading) {
            Text(
                text = "No games found",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = games.itemCount,
                    key = games.itemKey { it.id },
                    contentType = games.itemContentType { "game" }
                ) { index ->
                    val game = games[index]
                    if (game != null) {
                        GameItem(
                            game = game,
                            onClick = { onNavigateToDetails(game.id) }
                        )
                    }
                }

                if (appendState is LoadState.Loading) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (appendState is LoadState.Error) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Button(
                            onClick = { games.retry() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}
