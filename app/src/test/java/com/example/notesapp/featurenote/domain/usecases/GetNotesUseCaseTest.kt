package com.example.notesapp.featurenote.domain.usecases

import com.example.notesapp.featurenote.data.repository.FakeNoteRepositoryImpl
import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.util.NoteOrderBy
import com.example.notesapp.featurenote.domain.util.NoteSortBy
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {

    private lateinit var getNotesUseCase: GetNotesUseCase

    @Before
    fun setUp() {

        val fakeNoteRepository = FakeNoteRepositoryImpl()
        getNotesUseCase = GetNotesUseCase(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { i, c ->
            notesToInsert.add(
                Note(title = c.toString(), content = c.toString(), timestamp = i.toLong(), color = i)
            )
        }
        notesToInsert.shuffle()

        runBlocking {
            notesToInsert.forEach { fakeNoteRepository.insertNote(it) }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`() = runBlocking {

        val noteList = getNotesUseCase(NoteOrderBy.Title(NoteSortBy.Ascending)).first()
        for (i in 0..noteList.size - 2) {
            assertThat(noteList[i].title).isLessThan(noteList[i+1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`() = runBlocking {

        val noteList = getNotesUseCase(NoteOrderBy.Title(NoteSortBy.Descending)).first()
        for (i in 0..noteList.size - 2) {
            assertThat(noteList[i].title).isGreaterThan(noteList[i+1].title)
        }
    }
}