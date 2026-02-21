package com.example.notesapp.featurenote.presentation.notes

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.util.NoteOrderBy

sealed interface NotesEvent {
    data class Order(val noteOrderBy: NoteOrderBy): NotesEvent
    data class DeleteNote(val note: Note): NotesEvent
    object RestoreNote: NotesEvent
    object ToggleOrderSelection: NotesEvent

    data class AddNote(val note: Note): NotesEvent
}