package com.example.musicbrainzexplorer.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicbrainzexplorer.databinding.RowAlbumBinding
import com.example.musicbrainzexplorer.remote.model.ReleaseGroup
import com.example.musicbrainzexplorer.util.longFormat
import com.example.musicbrainzexplorer.util.setTextAndVisibility

class AlbumsAdapter(
    private val albums: List<ReleaseGroup>
) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowAlbumBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount() = albums.size

    class ViewHolder(
        private val binding: RowAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: ReleaseGroup) {
            binding.albumName.text = album.title
            binding.releaseYear.setTextAndVisibility(album.firstReleaseDate?.longFormat())
        }
    }
}
