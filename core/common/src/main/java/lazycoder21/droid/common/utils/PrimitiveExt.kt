package lazycoder21.droid.common.utils

val String?.orBlank get() = this ?: ""

val Int.isFirst get() = this == 0