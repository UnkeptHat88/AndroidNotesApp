package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}