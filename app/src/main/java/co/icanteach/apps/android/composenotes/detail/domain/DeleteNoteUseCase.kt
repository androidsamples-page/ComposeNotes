package co.icanteach.apps.android.composenotes.detail.domain

import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.data.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
) {

    suspend fun deleteNote(note: Note) {
        repository.deleteNote(note.id)
    }
}