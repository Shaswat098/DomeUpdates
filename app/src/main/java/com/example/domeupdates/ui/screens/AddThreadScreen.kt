package com.example.domeupdates.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domeupdates.viewmodel.ThreadViewModel
import com.example.domeupdates.ui.theme.ThreadsAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddThreadScreen(
    viewModel: ThreadViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    ThreadsAppTheme(darkTheme = true) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Add Thread") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (title.isNotBlank() && desc.isNotBlank()) {
                            viewModel.addThread(title, desc)
                            onBack() // Navigate back once the thread is added
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Post Thread")
                }
            }
        }
    }
}
