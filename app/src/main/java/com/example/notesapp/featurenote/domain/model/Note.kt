package com.example.notesapp.featurenote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.featurenote.presentation.ui.theme.BabyBlue
import com.example.notesapp.featurenote.presentation.ui.theme.LightGreen
import com.example.notesapp.featurenote.presentation.ui.theme.RedOrange
import com.example.notesapp.featurenote.presentation.ui.theme.RedPink
import com.example.notesapp.featurenote.presentation.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(RedOrange, BabyBlue, Violet, RedPink, LightGreen)
    }
}

class InvalidNoteException(message: String): Exception(message)
