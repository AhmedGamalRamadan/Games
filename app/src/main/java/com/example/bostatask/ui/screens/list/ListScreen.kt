package com.example.bostatask.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bostatask.ui.components.GameItem
import com.example.domain.model.list.Result
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = koinViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    val selectedSlug by viewModel.selectedSlug.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val games = viewModel.gamesPagingData.collectAsLazyPagingItems()

    val slugs = listOf("action", "adventure", "shooter", "role-playing-games-rpg")

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search Games") }
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(slugs) { slug ->
                FilterChip(
                    selected = (slug == selectedSlug),
                    onClick = { viewModel.onSlugSelected(slug) },
                    label = { Text(slug.replaceFirstChar { it.uppercase() }) }
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when {
                games.loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                games.loadState.refresh is LoadState.Error -> {
                    val error = games.loadState.refresh as LoadState.Error
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Error: ${error.error.localizedMessage}",
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = { games.retry() }) {
                            Text("Retry")
                        }
                    }
                }

                games.itemCount == 0 -> {
                    Text(text = "No games found", modifier = Modifier.align(Alignment.Center))
                }

                else -> {
                    val filteredGames = mutableListOf<Result>()
                    for (i in 0 until games.itemCount) {
                        val game = games[i]
                        if (game != null) {
                            if (searchQuery.isEmpty() || game.name?.contains(
                                    searchQuery,
                                    ignoreCase = true
                                ) == true
                            ) {
                                filteredGames.add(game)
                            }
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredGames) { game ->
                            GameItem(
                                game = game,
                                onClick = { game.id?.let { onNavigateToDetails(it) } })
                        }

                        if (games.loadState.append is LoadState.Loading) {
                            item {
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
                        if (games.loadState.append is LoadState.Error) {
                            item {
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
    }
}
