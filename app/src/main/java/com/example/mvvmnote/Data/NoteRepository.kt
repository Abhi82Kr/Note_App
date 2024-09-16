package com.example.mvvmnote.Data

import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private  val noteDao: NoteDao
) {
    fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun getNoteById(Id: Long):Flow<Note?> {
        return  noteDao.getNoteById(Id)
    }

    suspend fun InsertNote(note: Note) {
        noteDao.InsertNote(note)
    }

    suspend fun DeleteNote(note: Note) {
        noteDao.DeleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}