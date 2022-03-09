package co.icanteach.apps.android.composenotes.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getFormattedDate(timeStamp: Long, dateFormat: Format): String {
        val sdf = SimpleDateFormat(dateFormat.format, Locale.getDefault())
        val netDate = Date(timeStamp)
        return sdf.format(netDate)
    }

    enum class Format(val format: String) {
        DAY_HOUR_FORMAT("dd MMMM, HH:mm"),
        ONLY_DAY_FORMAT("dd MMMM")
    }
}