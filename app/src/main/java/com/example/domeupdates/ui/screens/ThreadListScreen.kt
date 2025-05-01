package com.example.domeupdates.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domeupdates.data.model.ThreadEntity
import com.example.domeupdates.ui.components.ThreadCard
import com.example.domeupdates.viewmodel.ThreadViewModel
import com.example.domeupdates.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreadListScreen(
    threads: List<ThreadEntity>,
    viewModel: ThreadViewModel,
    onAdd: () -> Unit,
    onThreadClick: (ThreadEntity) -> Unit,
    onShareClick: (ThreadEntity) -> Unit,
    onCommentClick: (ThreadEntity) -> Unit,
    onBookmarkClick: (ThreadEntity) -> Unit,
    onEdit: (ThreadEntity) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("DomeUpdates") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAdd() },
                containerColor = OrangeColor,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Thread")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(threads) { thread ->
                ThreadCard(
                    thread = thread,
                    viewModel = viewModel,
                    onClick = { onThreadClick(thread) },
                    onShareClick = onShareClick,
                    onCommentClick = onCommentClick,
                    onBookmarkClick = onBookmarkClick,
                    onEditThread = { onEdit(thread) }
                )
            }
        }
    }
}
