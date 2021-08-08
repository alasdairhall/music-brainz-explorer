package com.example.musicbrainzexplorer.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicbrainzexplorer.databinding.RowSearchResultBinding
import com.example.musicbrainzexplorer.remote.model.Artist

class SearchResultAdapter(
    private val artists: List<Artist>
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowSearchResultBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount() = artists.size

    class ViewHolder(private val itemBinding: RowSearchResultBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(artist: Artist) {
            itemBinding.artistName.text = artist.name
            if (artist.area != null) {
                itemBinding.artistArea.text = artist.area.name
                itemBinding.artistArea.visibility = View.VISIBLE
            } else {
                itemBinding.artistArea.visibility = View.GONE
            }

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, artist.id, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
