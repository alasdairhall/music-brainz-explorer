package com.example.musicbrainzexplorer.remote.model.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class LocalDateAdapter {
    @FromJson
    fun fromJson(dateString: String): LocalDate? = try {
        LocalDate.parse(dateString)
    } catch (e: DateTimeParseException) {
        null
    }

    @ToJson
    fun toJson(localDate: LocalDate): String = localDate.format(DateTimeFormatter.ISO_DATE)
}