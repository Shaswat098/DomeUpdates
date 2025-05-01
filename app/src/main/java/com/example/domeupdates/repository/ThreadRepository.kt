package com.example.domeupdates.repository

import com.example.domeupdates.data.db.ThreadDao
import com.example.domeupdates.data.model.CommentEntity
import com.example.domeupdates.data.model.ThreadEntity
import com.example.domeupdates.data.db.CommentDao

import kotlinx.coroutines.flow.Flow

class ThreadRepository(
    private val threadDao: ThreadDao,
    private val commentDao: CommentDao
) {
    val allThreads: Flow<List<ThreadEntity>> = threadDao.getAllThreads()

    fun getThreadById(threadId: Long): Flow<ThreadEntity?> {
        return threadDao.observeThreadById(threadId)
    }

    suspend fun getThreadOnce(threadId: Long): ThreadEntity? {
        return threadDao.getThreadById(threadId)
    }

    fun getComments(threadId: Long): Flow<List<CommentEntity>> =
        commentDao.getCommentsForThread(threadId)

    suspend fun addThread(thread: ThreadEntity) = threadDao.insertThread(thread)
    suspend fun addComment(comment: CommentEntity) = commentDao.insertComment(comment)
    suspend fun likeThread(threadId: Long, newCount: Int) =
        threadDao.updateLikeCount(threadId, newCount)

    suspend fun incrementView(threadId: Long) =
        threadDao.incrementViewCount(threadId)

    suspend fun deleteThread(threadId: Long) {
        threadDao.deleteThread(threadId)
        commentDao.deleteCommentsForThread(threadId)
    }

    suspend fun toggleBookmark(threadId: Long) {
        threadDao.toggleBookmark(threadId)
    }

    suspend fun updateThread(thread: ThreadEntity) {
        threadDao.updateThread(thread)
    }


}
