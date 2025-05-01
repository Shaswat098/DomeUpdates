package com.example.domeupdates.data.db

import androidx.room.*
import com.example.domeupdates.data.model.ThreadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThreadDao {

    @Query("SELECT * FROM threads")
    fun getAllThreads(): Flow<List<ThreadEntity>>


    @Query("SELECT * FROM threads WHERE id = :threadId LIMIT 1")
    fun observeThreadById(threadId: Long): Flow<ThreadEntity?>

    @Query("SELECT * FROM threads WHERE id = :threadId LIMIT 1")
    suspend fun getThreadById(threadId: Long): ThreadEntity?

    @Insert
    suspend fun insertThread(thread: ThreadEntity)

    @Query("UPDATE threads SET likeCount = :likeCount WHERE id = :threadId")
    suspend fun updateLikeCount(threadId: Long, likeCount: Int)

    @Query("UPDATE threads SET viewCount = viewCount + 1 WHERE id = :threadId")
    suspend fun incrementViewCount(threadId: Long)

    @Query("DELETE FROM threads WHERE id = :threadId")
    suspend fun deleteThread(threadId: Long)

    @Query("UPDATE threads SET isBookmarked = NOT isBookmarked WHERE id = :threadId")
    suspend fun toggleBookmark(threadId: Long)

    @Update
    suspend fun updateThread(thread: ThreadEntity)

}