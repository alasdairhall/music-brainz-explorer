package com.example.musicbrainzexplorer.remote.model.adapters

import com.example.musicbrainzexplorer.remote.model.LifeSpanResponse
import com.example.musicbrainzexplorer.remote.model.Status
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class StatusAdapter {
    @FromJson
    fun fromJson(lifeSpanResponse: LifeSpanResponse): Status? {
        return with(lifeSpanResponse) {
            val beginYear = begin?.year()
            val endYear = end?.year()
            when {
                beginYear != null && endYear != null && ended == true -> Status.Ended(
                    beginYear,
                    endYear
                )
                beginYear != null && endYear == null && ended != true -> Status.Active(beginYear)
                else -> null
            }
        }
    }

    private fun String.year() = split("-").getOrNull(0)

    @ToJson
    fun toJson(status: Status): LifeSpanResponse {
        return when (status) {
            is Status.Active -> LifeSpanResponse(begin = status.begin, end = null, ended = false)
            is Status.Ended -> LifeSpanResponse(
                begin = status.begin,
                end = status.end,
                ended = true
            )
        }
    }
}