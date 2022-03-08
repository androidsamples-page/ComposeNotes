package co.icanteach.apps.android.composenotes.data

import co.icanteach.apps.android.composenotes.data.ColorGenerator.getColor

data class Note(
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null,
) {
    companion object {

        val Default = Note(
            content = "",
            timestamp = System.currentTimeMillis(),
            color = getColor()
        )
    }
}