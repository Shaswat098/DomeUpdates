package com.example.domeupdates.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domeupdates.data.model.CommentEntity
import com.example.domeupdates.data.model.ThreadEntity


@Database(entities = [ThreadEntity::class, CommentEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun threadDao(): ThreadDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "threads_db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
    }
}
