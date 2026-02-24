package com.example.notesapp.featurenote.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.featurenote.domain.util.NoteOrderBy
import com.example.notesapp.featurenote.domain.util.NoteSortBy

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy = NoteOrderBy.Date(NoteSortBy.Descending),
    onOrderChange: (NoteOrderBy) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                checked = noteOrderBy is NoteOrderBy.Title,
                onChecked = { onOrderChange(NoteOrderBy.Title(noteOrderBy.sortBy)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                checked = noteOrderBy is NoteOrderBy.Date,
                onChecked = { onOrderChange(NoteOrderBy.Date(noteOrderBy.sortBy)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Color",
                checked = noteOrderBy is NoteOrderBy.Color,
                onChecked = { onOrderChange(NoteOrderBy.Color(noteOrderBy.sortBy)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                checked = noteOrderBy.sortBy is NoteSortBy.Ascending,
                onChecked = { onOrderChange(noteOrderBy.copy(NoteSortBy.Ascending)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                checked = noteOrderBy.sortBy is NoteSortBy.Descending,
                onChecked = { onOrderChange(noteOrderBy.copy(NoteSortBy.Descending)) }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OrderSection() { }
}