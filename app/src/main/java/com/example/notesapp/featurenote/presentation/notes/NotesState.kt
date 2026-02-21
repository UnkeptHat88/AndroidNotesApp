package com.example.notesapp.featurenote.presentation.notes

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.util.NoteOrderBy
import com.example.notesapp.featurenote.domain.util.NoteSortBy

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrderBy = NoteOrderBy.Date(NoteSortBy.Descending),
    val isOrderSelectionVisible: Boolean = false
)
