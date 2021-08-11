package com.example.musicbrainzexplorer.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.longFormat(): String =
    DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault()).format(this)
