package com.example.musicbrainzexplorer.remote.model.adapters

import com.example.musicbrainzexplorer.remote.model.ReleaseType
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseTypeAdapterTest {
    private val adapter = ReleaseTypeAdapter()

    @Test
    fun `maps valid release type to enum`() {
        assertEquals(ReleaseType.Album, adapter.fromJson("Album"))
        assertEquals(ReleaseType.EP, adapter.fromJson("EP"))
    }

    @Test
    fun `maps invalid release type to Other`() {
        assertEquals(ReleaseType.Other, adapter.fromJson("Compilation"))
    }

    @Test
    fun `maps release type to json`() {
        assertEquals("Album", adapter.toJson(ReleaseType.Album))
        assertEquals("EP", adapter.toJson(ReleaseType.EP))
    }
}