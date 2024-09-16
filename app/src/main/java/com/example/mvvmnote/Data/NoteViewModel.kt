package com.example.mvvmnote.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnote.Data.Note
import com.example.mvvmnote.Data.NoteRepository
import com.example.mvvmnote.Graph
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
) : ViewModel() {

    // StateFlow to hold a list of notes
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        getNotes()
    }

    // Function to fetch all notes
    private fun getNotes() {
        noteRepository.getNotes()
            .onEach { noteList ->
                _notes.value = noteList
            }
            .launchIn(viewModelScope)
    }

    // Function to fetch a note by its ID
    fun getNoteById(id: Long): StateFlow<Note?> {
        return _notes
            .map { notesList ->
                notesList.find { it.id == id }
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    // Function to insert a new note
    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.InsertNote(note)
            getNotes() // Refresh notes after insertion
        }
    }

    // Function to delete a note
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.DeleteNote(note)
            getNotes() // Refresh notes after deletion
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.updateNote(note)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
