package com.example.musicbrainzexplorer.remote.model.adapters

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.LocalDate

class LocalDateAdapterTest {
    private val adapter = LocalDateAdapter()

    @Test
    fun `can parse local date from string`() {
        assertEquals(LocalDate.of(1962, 10, 12), adapter.fromJson("1962-10-12"))
        assertNull(adapter.fromJson("1962-10"))
        assertNull(adapter.fromJson("1962"))
    }

    @Test
    fun `can re-format string from local date`() {
        assertEquals("1962-10-12", adapter.toJson(LocalDate.of(1962, 10, 12)))
    }
}
