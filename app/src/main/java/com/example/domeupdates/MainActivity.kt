package com.example.domeupdates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.domeupdates.ui.screens.AddThreadScreen
import com.example.domeupdates.ui.screens.EditThreadScreen
import com.example.domeupdates.ui.screens.ThreadDetailScreen
import com.example.domeupdates.ui.screens.ThreadListScreen
import com.example.domeupdates.viewmodel.ThreadViewModel
import com.example.domeupdates.ui.theme.ThreadsAppTheme
import com.example.domeupdates.data.model.ThreadEntity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreadsAppTheme {
                val vm: ThreadViewModel = viewModel()
                var screen by remember { mutableStateOf("list") }
                var selectedId by remember { mutableStateOf(0L) }
                var threadToEdit by remember { mutableStateOf<ThreadEntity?>(null) }

                val threadList by vm.threads.observeAsState(emptyList())

                when (screen) {
                    "list" -> ThreadListScreen(
                        threads = threadList,
                        viewModel = vm,
                        onThreadClick = { thread ->
                            selectedId = thread.id
                            screen = "detail"
                        },
                        onShareClick = { thread -> },
                        onCommentClick = { thread ->
                            selectedId = thread.id
                            screen = "detail"
                        },
                        onBookmarkClick = { thread ->
                            vm.toggleBookmark(thread.id)
                        },
                        onAdd = {
                            screen = "add"
                        },
                        onEdit = { thread ->
                            threadToEdit = thread
                            screen = "edit"
                        }
                    )

                    "add" -> AddThreadScreen(vm) {
                        screen = "list"
                    }

                    "detail" -> ThreadDetailScreen(selectedId, vm) {
                        screen = "list"
                    }

                    "edit" -> EditThreadScreen(
                        thread = threadToEdit,
                        viewModel = vm,
                        onSave = { updatedThread ->
                            updatedThread?.let {
                                vm.updateThread(it)  // Update the thread in ViewModel
                                screen = "list"  // Navigate back to list after saving
                            }
                        },
                        onCancel = {
                            screen = "list"  // Cancel edit and return to list
                        }
                    )
                }
            }
        }
    }
}
