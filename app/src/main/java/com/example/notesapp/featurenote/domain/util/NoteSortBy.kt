package com.example.notesapp.featurenote.domain.util

sealed interface NoteSortBy {
    object Ascending: NoteSortBy
    object Descending: NoteSortBy
}