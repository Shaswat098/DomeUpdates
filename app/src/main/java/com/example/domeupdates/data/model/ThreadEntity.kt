package com.example.domeupdates.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "threads")
data class ThreadEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    var likeCount: Int = 0,
    var viewCount: Int = 0,
    val isBookmarked: Boolean = false

)
