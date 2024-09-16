package com.example.mvvmnote.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes():Flow<List<Note>>


    @Query("SELECT  * FROM note WHERE id= :id")
    fun getNoteById(id:Long):Flow<Note?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertNote(note: Note)

    @Delete
    suspend fun DeleteNote(note: Note)

    @Update
    abstract suspend fun updateNote(note: Note)
}