package com.alvaro.civitatis.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alvaro.civitatis.R
import com.alvaro.civitatis.databinding.FragmentCharacterDetailsBinding
import com.alvaro.civitatis.viewmodel.CharacterDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class CharacterDetailsFragment: Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel by viewModels<CharacterDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(viewLifecycleOwner) {

        }

        viewModel.character.observe(viewLifecycleOwner) {
            binding.apply {
                val character = viewModel.character.value
                if (character != null) {
                    Glide.with(root.context).load(character.imageUrl).dontAnimate()
                        .into(characterDetailIv)
                    characterNameTv.text = character.name
                    characterDescriptionTv.text = character.description.ifEmpty {
                        getString(R.string.description_not_available)
                    }
                    characterComicsTv.text = if (character.comics.isNotEmpty()) {
                        character.comics.replace(",", "\n")
                    } else {
                        getString(R.string.comics_not_available)
                    }
                    characterSeriesTv.text = if (character.series.isNotEmpty()) {
                        character.series.replace(",", "\n")
                    } else {
                        getString(R.string.series_not_available)
                    }
                    characterStoriesTv.text = if (character.stories.isNotEmpty()) {
                        character.stories.replace(",", "\n")
                    } else {
                        getString(R.string.stories_not_available)
                    }
                    if (character.urls.isNotEmpty()) {
                        addUrlTextView(character.urls)
                    } else {
                        val textView = TextView(requireContext())
                        textView.text = getString(R.string.urls_not_available)
                        binding.characterUrlsLl.addView(textView)
                    }
                }
            }
        }

        if (viewModel.loading.value == true) {
            viewModel.getCharacterDetails(requireArguments().getString("id", "0"))
        }
    }

    private fun addUrlTextView(urls: String) {
        val urls = urls.split(",")
        urls.forEach { url ->
            val textView = TextView(requireContext())
            textView.text = url.replace(",", "\n\n")
            textView.setTextColor(requireContext().getColor(R.color.link))
            textView.setOnClickListener {
                val action = CharacterDetailsFragmentDirections
                    .actionCharacterDetailsToWebViewFragment(
                        url
                            .replace("\n", "")
                            .replace("http", "https")
                    )
                findNavController().navigate(action)
            }
            binding.characterUrlsLl.addView(textView)
        }
    }

}