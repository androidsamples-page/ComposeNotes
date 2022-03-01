package co.icanteach.apps.android.composenotes.data


data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null,
) {
    companion object {
        // val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}