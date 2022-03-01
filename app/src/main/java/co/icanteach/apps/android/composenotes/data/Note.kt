package co.icanteach.apps.android.composenotes.data


data class Note(
    val content: String,
    val timestamp: Long,
    val id: Int? = null,
) {
    companion object {
        // val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}