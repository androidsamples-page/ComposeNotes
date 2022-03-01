package co.icanteach.apps.android.composenotes.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}