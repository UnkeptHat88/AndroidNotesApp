package com.example.notesapp.featurenote.presentation.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.featurenote.domain.model.Note
import com.example.notesapp.featurenote.domain.usecases.NoteUseCases
import com.example.notesapp.featurenote.domain.util.NoteOrderBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state: MutableState<NotesState> = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(_state.value.noteOrder)
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = notesEvent.note
                    noteUseCases.deleteNoteUseCase(notesEvent.note)
                }
            }

            is NotesEvent.Order -> {
                if (_state.value.noteOrder::class == notesEvent.noteOrderBy::class
                    && _state.value.noteOrder.sortBy == notesEvent.noteOrderBy.sortBy
                )
                    return

                _state.value = _state.value.copy(
                    noteOrder = notesEvent.noteOrderBy
                )
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            NotesEvent.ToggleOrderSelection -> {
                _state.value = _state.value.copy(
                    isOrderSelectionVisible = !_state.value.isOrderSelectionVisible
                )
            }

            is NotesEvent.AddNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(notesEvent.note)
                }
            }
        }
    }

    private fun getNotes(noteOrderBy: NoteOrderBy) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase(noteOrderBy).onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes,
                noteOrder = noteOrderBy
            )
        }.launchIn(viewModelScope)
    }
}
