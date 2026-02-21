package com.example.notesapp.featurenote.domain.util

sealed class NoteOrderBy(val sortBy: NoteSortBy) {
    class Title(noteSortBy: NoteSortBy): NoteOrderBy(noteSortBy)
    class Date(noteSortBy: NoteSortBy): NoteOrderBy(noteSortBy)
    class Color(noteSortBy: NoteSortBy): NoteOrderBy(noteSortBy)

    fun copy(sortBy: NoteSortBy): NoteOrderBy {
        return when (this) {
            is Color -> NoteOrderBy.Color(sortBy)
            is Date -> NoteOrderBy.Date(sortBy)
            is Title -> NoteOrderBy.Title(sortBy)
        }
    }
}
