package com.example.pokeapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapi.data.network.models.PokemonModel
import com.example.pokeapi.databinding.PokemonItemBinding
import com.example.pokeapi.ui.adapters.PokemonAdapter.PokemonViewHolder

class PokemonAdapter(private val list: MutableList<PokemonModel>) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(pokemonModel: PokemonModel) {
            binding.tvNamePokemon.text = pokemonModel.name
            val pokemonId = pokemonModel.url.replace("v2", "").filter { it.isDigit() }
            Glide.with(binding.ivImagePokemon)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png")
                .into(binding.ivImagePokemon)
        }
    }
}