package lazycoder21.droid.common.enitity

data class DateTime(
    val date: String = "",
    val time: String = "",
) {
    companion object {
        val Default = DateTime()
    }
}
