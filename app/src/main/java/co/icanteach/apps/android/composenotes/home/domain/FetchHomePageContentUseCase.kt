package co.icanteach.apps.android.composenotes.home.domain

import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.data.NoteMapper
import co.icanteach.apps.android.composenotes.data.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class FetchHomePageContentUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteMapper,
) {

    fun getNotes(
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            mapper.map(notes)
        }
    }
}