package com.example.mvvmnote

import android.content.Context
import androidx.room.Room
import com.example.mvvmnote.Data.NoteDatabase
import com.example.mvvmnote.Data.NoteRepository

object Graph {
    private var database: NoteDatabase? = null

    val noteRepository: NoteRepository by lazy {
        NoteRepository(noteDao = getDatabase().noteDao())
    }

    // Call this method in your Application class to initialize the database
    fun provide(context: Context) {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java, "notelist.db"
            ).build()
        }
    }

    // Helper function to get the database instance
    private fun getDatabase(): NoteDatabase {
        return database ?: throw IllegalStateException("Database must be initialized using provide(context) before accessing it")
    }
}
