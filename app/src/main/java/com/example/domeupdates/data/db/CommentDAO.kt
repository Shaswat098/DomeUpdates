package com.example.domeupdates.data.db

import androidx.room.*
import com.example.domeupdates.data.model.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments WHERE threadId = :threadId")
    fun getCommentsForThread(threadId: Long): Flow<List<CommentEntity>>

    @Insert
    suspend fun insertComment(comment: CommentEntity)

    @Query("DELETE FROM comments WHERE threadId =  :threadId")
    suspend fun deleteCommentsForThread(threadId: Long)
}
