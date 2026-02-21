package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.domain.model.InvalidNoteException
import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw (InvalidNoteException("The title of the note is blank!"))
        }

        if (note.content.isBlank()) {
            throw (InvalidNoteException("The content of the note is blank!"))
        }

        repository.insertNote(note)
    }
}