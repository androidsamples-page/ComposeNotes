package co.icanteach.apps.android.composenotes.detail.domain

import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.data.NoteMapper
import co.icanteach.apps.android.composenotes.data.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    val repository: NoteRepository,
    val mapper: NoteMapper,
) {

    suspend fun createNote(note: Note) {
        repository.insertNote(mapper.mapFrom(note = note))
    }
}