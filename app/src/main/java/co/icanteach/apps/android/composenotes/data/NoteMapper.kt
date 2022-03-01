package co.icanteach.apps.android.composenotes.data

import javax.inject.Inject

class NoteMapper @Inject constructor()  {

    fun mapFrom(note: Note): NoteEntity {
        return NoteEntity(
            title = note.title,
            color = 1,
            content = note.content,
            timestamp = note.timestamp,
        )
    }

    fun map(note: NoteEntity): Note {
        return Note(
            title = note.title,
            color = 1,
            content = note.content,
            timestamp = note.timestamp,
            id = note.id ?: -1
        )
    }

    fun map(notes: List<NoteEntity>): List<Note> {
        return notes.map { note ->
            Note(
                title = note.title,
                color = 1,
                content = note.content,
                timestamp = note.timestamp,
                id = note.id ?: -1
            )
        }
    }
}