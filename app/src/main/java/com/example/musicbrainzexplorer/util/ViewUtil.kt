package com.example.musicbrainzexplorer.util

import android.view.View
import android.widget.TextView

fun TextView.setTextAndVisibility(newText: String?) {
    text = newText
    visibility = if (newText != null) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
