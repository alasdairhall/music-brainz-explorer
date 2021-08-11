package com.example.musicbrainzexplorer.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class DateUtilTest {
    @Test
    fun `longFormat formats LocalDate with day, full month and year`() {
        val date = LocalDate.of(1989, 10, 1)

        assertEquals("1 October 1989", date.longFormat())
    }
}
