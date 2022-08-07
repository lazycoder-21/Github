package lazycoder21.droid.common.utils

import junit.framework.TestCase
import lazycoder21.droid.common.constant.DefaultValues
import org.junit.Test

class PrimitiveExtKtTest : TestCase() {

    @Test
    fun `test orBlank for null string`() {
        val text: String? = null
        val actual = text.orBlank

        assertEquals(actual, "")
    }

    @Test
    fun `test orBlank for normal string`() {
        val text: String? = "null"
        val actual = text.orBlank

        assertEquals(actual, "null")
    }

    @Test
    fun `test isFirstPage - success`() {
        val num = DefaultValues.PAGE_NO
        val actual = num.isFirstPage
        assertTrue(actual)
    }

    @Test
    fun `test isFirstPage - failure`() {
        val num = DefaultValues.PAGE_NO + 1
        val actual = num.isFirstPage
        assertEquals(actual, false)
    }
}