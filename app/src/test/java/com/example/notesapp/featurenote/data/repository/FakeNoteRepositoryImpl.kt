package com.example.notesapp.featurenote.data.repository

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepositoryImpl: NoteRepository {

    private val noteList = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(noteList)
        }
    }

    override suspend fun getNoteByID(id: Int): Note? {
        return noteList.find { it.id == id }
    }

    override suspend fun insertNote(note: Note) {
        noteList.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteList.removeIf { it.id == note.id }
    }
}