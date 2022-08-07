package lazycoder21.droid.common.utils

import lazycoder21.droid.common.enitity.DateTime
import java.text.SimpleDateFormat
import java.util.*

fun String.splitDateTime(): DateTime = try {
    val dateTime = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US
    ).parse(this) ?: DateTime.Default

    val date = SimpleDateFormat("dd/MM/yyy", Locale.US).format(dateTime)
    val time = SimpleDateFormat("HH:mm", Locale.US).format(dateTime)
    DateTime(date, time)
} catch (e: Exception) {
    DateTime.Default
}