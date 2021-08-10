package com.example.musicbrainzexplorer.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicbrainzexplorer.databinding.RowSearchResultBinding
import com.example.musicbrainzexplorer.remote.model.Artist

class SearchResultAdapter(
    private val artists: List<Artist>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowSearchResultBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount() = artists.size

    class ViewHolder(
        private val itemBinding: RowSearchResultBinding,
        private val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(artist: Artist) {
            itemBinding.artistName.text = artist.name
            if (artist.area != null) {
                itemBinding.artistArea.text = artist.area.name
                itemBinding.artistArea.visibility = View.VISIBLE
            } else {
                itemBinding.artistArea.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onClick(artist.id)
            }
        }
    }
}
