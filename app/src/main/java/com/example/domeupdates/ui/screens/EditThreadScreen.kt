package com.example.domeupdates.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domeupdates.viewmodel.ThreadViewModel
import com.example.domeupdates.data.model.ThreadEntity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.livedata.observeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditThreadScreen(
    thread: ThreadEntity?,
    viewModel: ThreadViewModel,
    onSave: (ThreadEntity?) -> Unit,  // Callback for saving the thread
    onCancel: () -> Unit  // Callback for cancel action
) {
    var title by remember { mutableStateOf(thread?.title ?: "") }
    var description by remember { mutableStateOf(thread?.description ?: "") }

    LaunchedEffect(thread) {
        thread?.let {
            title = it.title
            description = it.description
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Thread") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        val updatedThread = thread?.copy(title = title, description = description)
                        updatedThread?.let {
                            onSave(it)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }
        }
    }
}
