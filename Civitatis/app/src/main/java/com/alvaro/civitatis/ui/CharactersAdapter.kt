package com.alvaro.civitatis.ui

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvaro.civitatis.R
import com.alvaro.civitatis.databinding.CharacterCardBinding
import com.alvaro.civitatis.model.DomainCharacter
import com.bumptech.glide.Glide

class CharactersAdapter(
    private val onClickListener: (DomainCharacter) -> Unit
): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    var characters: List<DomainCharacter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener{ onClickListener(characters[position]) }
    }

    class ViewHolder(private val binding: CharacterCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: DomainCharacter) {
            comic.imageUrl.let { Glide.with(binding.root.context).load(it).dontAnimate().into(binding.comicIv) }
            comic.title.let { binding.comicTitleTv.text = it }
            comic.description.let {
                binding.comicDescriptionTv.text = if (it.isNotBlank()) it
                else binding.root.context.getString(R.string.description_not_available)
            }
        }
    }
}