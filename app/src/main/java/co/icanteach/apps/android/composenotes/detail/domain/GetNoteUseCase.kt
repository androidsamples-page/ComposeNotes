package co.icanteach.apps.android.composenotes.detail.domain

import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.data.NoteMapper
import co.icanteach.apps.android.composenotes.data.NoteRepository
import java.lang.Exception
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteMapper,
) {

    suspend fun getNote(id: Int): Note {
        val result = repository.getNoteById(id)
        return mapper.map(result ?: mapper.map(Note.Default))
    }
}