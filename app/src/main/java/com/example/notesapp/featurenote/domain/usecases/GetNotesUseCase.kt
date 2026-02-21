package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.repository.NoteRepository
import com.example.notesapp.featurenote.domain.util.NoteOrderBy
import com.example.notesapp.featurenote.domain.util.NoteSortBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(
        noteOrderBy: NoteOrderBy = NoteOrderBy.Date(NoteSortBy.Descending)
    ): Flow<List<Note>> {

        return noteRepository.getNotes().map { rawListOfNotes: List<Note> ->
            when (noteOrderBy.sortBy) {
                NoteSortBy.Ascending -> {
                    when (noteOrderBy) {
                        is NoteOrderBy.Color -> rawListOfNotes.sortedBy { note -> note.color }
                        is NoteOrderBy.Date -> rawListOfNotes.sortedBy { note -> note.timestamp }
                        is NoteOrderBy.Title -> rawListOfNotes.sortedBy { note -> note.title.lowercase() }
                    }
                }
                NoteSortBy.Descending -> {
                    when (noteOrderBy) {
                        is NoteOrderBy.Color -> rawListOfNotes.sortedByDescending { note -> note.color }
                        is NoteOrderBy.Date -> rawListOfNotes.sortedByDescending { note -> note.timestamp }
                        is NoteOrderBy.Title -> rawListOfNotes.sortedByDescending { note -> note.title.lowercase() }
                    }
                }
            }
        }
    }
}