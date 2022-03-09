package co.icanteach.apps.android.composenotes

import co.icanteach.apps.android.composenotes.data.NoteEntity
import co.icanteach.apps.android.composenotes.data.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
        return flow {
            emit(emptyList())
        }
    }

    override suspend fun getNoteById(id: Int): NoteEntity {
        return NoteEntity.Default
    }

    override suspend fun insertNote(note: NoteEntity) {
        //
    }

    override suspend fun deleteNote(id: Int?) {
        //
    }
}