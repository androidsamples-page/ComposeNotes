package co.icanteach.apps.android.composenotes.data

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<NoteEntity>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(id: Int?) {
        dao.deleteNote(id)
    }
}