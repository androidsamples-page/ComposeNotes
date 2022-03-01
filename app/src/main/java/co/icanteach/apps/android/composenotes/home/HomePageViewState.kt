package co.icanteach.apps.android.composenotes.home

import co.icanteach.apps.android.composenotes.data.Note

data class HomePageViewState(
    val notes: List<Note> = emptyList(),
)