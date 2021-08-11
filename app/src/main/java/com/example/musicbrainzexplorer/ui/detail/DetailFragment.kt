package com.example.musicbrainzexplorer.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicbrainzexplorer.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var factory: DetailViewModel.Factory
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(factory, args.artistId)
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.albumsList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.artistDetail.observe(viewLifecycleOwner) { artist ->
            with(binding) {
                title.text = artist.name
                area.text = artist.beginArea?.name
                val lifeSpanText = "${artist.lifeSpan?.begin} - ${artist.lifeSpan?.end}"
                lifeSpan.text = lifeSpanText

                albumsList.adapter = AlbumsAdapter(artist.albums)
            }
        }
    }
}