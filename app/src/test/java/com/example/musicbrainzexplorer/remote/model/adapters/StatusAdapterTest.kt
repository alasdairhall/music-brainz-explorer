package com.example.musicbrainzexplorer.remote.model.adapters

import com.example.musicbrainzexplorer.remote.model.LifeSpanResponse
import com.example.musicbrainzexplorer.remote.model.Status
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StatusAdapterTest {
    private val adapter = StatusAdapter()

    @Test
    fun `life span with begin but no end is active, and date is shortened to just year`() {
        val lifeSpanResponse = LifeSpanResponse(begin = "2011-08-09", ended = null, end = null)

        val expectedStatus = Status.Active(begin = "2011")
        assertEquals(expectedStatus, adapter.fromJson(lifeSpanResponse))
    }

    @Test
    fun `life span with begin and end and ended is true Ended, and date is shortened to just year`() {
        val lifeSpanResponse = LifeSpanResponse(begin = "2011-06", ended = true, end = "2012-09-01")

        val expectedStatus = Status.Ended(begin = "2011", end = "2012")
        assertEquals(expectedStatus, adapter.fromJson(lifeSpanResponse))
    }

    @Test
    fun `invalid life span returns null`() {
        val lifeSpanResponse = LifeSpanResponse(begin = null, ended = null, end = null)

        assertNull(adapter.fromJson(lifeSpanResponse))
    }

    @Test
    fun `can map active to json`() {
        val active = Status.Active(begin = "2012")

        val expected = LifeSpanResponse(begin = "2012", end = null, ended = false)
        assertEquals(expected, adapter.toJson(active))
    }

    @Test
    fun `can map ended to json`() {
        val ended = Status.Ended(begin = "2012", end = "2016")

        val expected = LifeSpanResponse(begin = "2012", end = "2016", ended = true)
        assertEquals(expected, adapter.toJson(ended))
    }
}