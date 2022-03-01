package co.icanteach.apps.android.composenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null,
)