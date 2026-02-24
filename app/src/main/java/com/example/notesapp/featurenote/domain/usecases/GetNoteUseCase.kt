package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.repository.NoteRepository

class GetNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteByID(id)
    }
}