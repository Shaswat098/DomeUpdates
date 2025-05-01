package com.example.domeupdates.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domeupdates.data.model.ThreadEntity
import com.example.domeupdates.viewmodel.ThreadViewModel

@Composable
fun ThreadCard(
    thread: ThreadEntity,
    viewModel: ThreadViewModel,
    onClick: () -> Unit,
    onShareClick: (ThreadEntity) -> Unit,
    onCommentClick: (ThreadEntity) -> Unit,
    onBookmarkClick: (ThreadEntity) -> Unit,
    onEditThread: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
                viewModel.incrementView(thread.id)
            },
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = thread.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Options",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            onClick = {
                                expanded = false
                                onEditThread(thread.id)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                expanded = false
                                viewModel.deleteThread(thread)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Report") },
                            onClick = {
                                expanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = thread.description,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                IconButton(onClick = { viewModel.like(thread) }) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Like",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${thread.likeCount}",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                IconButton(onClick = { onCommentClick(thread) }) {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = "Comment",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(onClick = { onShareClick(thread) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(onClick = { onBookmarkClick(thread) }) {
                    val icon = if (thread.isBookmarked) {
                        Icons.Default.Bookmark
                    } else {
                        Icons.Default.BookmarkBorder
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                thickness = 1.dp,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
