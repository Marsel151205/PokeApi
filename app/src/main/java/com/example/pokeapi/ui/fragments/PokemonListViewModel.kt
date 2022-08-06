package com.example.pokeapi.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapi.data.network.models.PokemonModel
import com.example.pokeapi.data.repositories.PokeApiRepository

class PokemonListViewModel(private val repository: PokeApiRepository) : ViewModel() {

    fun getPokemonList(offset: Int, limit: Int) = repository.getPokemonList(offset, limit)
}