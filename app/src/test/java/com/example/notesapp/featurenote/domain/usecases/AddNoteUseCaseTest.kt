package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.data.repository.FakeNoteRepositoryImpl
import com.example.notesapp.featurenote.domain.model.InvalidNoteException
import com.example.notesapp.featurenote.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var getNotesUseCase: GetNotesUseCase

    @Before
    fun setUp() {

        val fakeNoteRepository = FakeNoteRepositoryImpl()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
        getNotesUseCase = GetNotesUseCase(fakeNoteRepository)
    }

    @Test
    fun `Add note with title as blank, throws InvalidNoteException`() {
        val note = Note(
            title = "",
            content = "lorem ipsum",
            timestamp = 1000,
            color = 1024
        )

        assertThrows<InvalidNoteException>(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(note)
            }
        }
    }

    @Test
    fun `Add note with content as blank, throws InvalidNoteException`() {
        val note = Note(
            title = "dolor ist",
            content = "",
            timestamp = 1000,
            color = 1024
        )

        assertThrows<InvalidNoteException>(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(note)
            }
        }
    }

    @Test
    fun `Add note to repository, new note exists in repository`() {
        val note = Note(
            title = "dolor est",
            content = "lorem ipsum",
            timestamp = 1000,
            color = 1024
        )

        runBlocking {
            addNoteUseCase(note)

            val retrievedNote = getNotesUseCase().first().first()
            assertThat(retrievedNote).isEqualTo(note)
        }
    }
}