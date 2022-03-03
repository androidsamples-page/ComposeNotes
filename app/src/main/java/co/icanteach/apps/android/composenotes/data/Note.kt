package co.icanteach.apps.android.composenotes.data

import co.icanteach.apps.android.composenotes.R


data class Note(
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null,
) {
    companion object {
        private val colors = listOf(
            R.color.card_color_1,
            R.color.card_color_2,
            R.color.card_color_3,
            R.color.card_color_4,
            R.color.card_color_5,
            R.color.card_color_6,
            R.color.card_color_7,
            R.color.card_color_8,
            R.color.card_color_9,
            R.color.card_color_10,
            R.color.card_color_11
        )

        private fun getColor() = colors.random()

        val Default = Note(
            content = "",
            timestamp = System.currentTimeMillis(),
            color = getColor()
        )
    }
}