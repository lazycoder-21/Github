package lazycoder21.droid.common.utils

import lazycoder21.droid.common.constant.DefaultValues

val String?.orBlank get() = this ?: ""

val Int.isFirstPage get() = this == DefaultValues.PAGE_NO