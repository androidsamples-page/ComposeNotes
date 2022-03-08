package co.icanteach.apps.android.composenotes.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getFormattedDate(timeStamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault())
        val netDate = Date(timeStamp)
        return sdf.format(netDate)
    }
}