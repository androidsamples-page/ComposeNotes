package co.icanteach.apps.android.composenotes.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Int): NoteEntity?

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(id: Int?)
}