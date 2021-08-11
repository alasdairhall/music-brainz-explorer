package com.example.musicbrainzexplorer.remote.model

import org.junit.Assert.assertEquals
import org.junit.Test

class StatusTest {
    @Test
    fun `active formats by begin year followed by dash`() {
        assertEquals("1972 -", Status.Active(begin = "1972"))
    }

    @Test
    fun `ended formats by begin year dash end year`() {
        assertEquals("1972 - 1992", Status.Ended(begin = "1972", end = "1992"))
    }
}