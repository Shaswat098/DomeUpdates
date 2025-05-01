package com.example.domeupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domeupdates.data.model.CommentEntity
import com.example.domeupdates.data.model.ThreadEntity
import com.example.domeupdates.repository.ThreadRepository
import com.example.domeupdates.data.db.AppDatabase
import kotlinx.coroutines.launch

class ThreadViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val repo = ThreadRepository(db.threadDao(), db.commentDao())

    val threads = repo.allThreads.asLiveData()

    // Fetch a specific thread by its ID
    fun getThread(threadId: Long): LiveData<ThreadEntity?> {
        return repo.getThreadById(threadId).asLiveData()
    }

    fun commentsFor(threadId: Long) = repo.getComments(threadId).asLiveData()

    fun addThread(title: String, desc: String) = viewModelScope.launch {
        repo.addThread(ThreadEntity(title = title, description = desc))
    }

    fun addComment(threadId: Long, text: String) = viewModelScope.launch {
        repo.addComment(CommentEntity(threadId = threadId, content = text))
    }

    fun like(thread: ThreadEntity) = viewModelScope.launch {
        repo.likeThread(thread.id, thread.likeCount + 1)
    }

    fun incrementView(threadId: Long) = viewModelScope.launch {
        repo.incrementView(threadId)
    }

    fun deleteThread(thread: ThreadEntity) = viewModelScope.launch {
        repo.deleteThread(thread.id)
    }

    fun toggleBookmark(threadId: Long) {
        viewModelScope.launch {
            repo.toggleBookmark(threadId)
        }
    }

    fun updateThread(updatedThread: ThreadEntity) {
        viewModelScope.launch {
            repo.getThreadById(updatedThread.id).collect { original ->
                if (original != null) {
                    val merged = original.copy(
                        title = updatedThread.title,
                        description = updatedThread.description
                    )
                    repo.updateThread(merged)
                }
            }
        }
    }





}
