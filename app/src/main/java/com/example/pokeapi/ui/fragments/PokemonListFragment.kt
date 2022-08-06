package com.example.pokeapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapi.data.network.RetrofitClient
import com.example.pokeapi.data.network.models.PokemonModel
import com.example.pokeapi.data.repositories.PokeApiRepository
import com.example.pokeapi.databinding.FragmentPokemonListBinding
import com.example.pokeapi.ui.adapters.PokemonAdapter

class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var viewModel: PokemonListViewModel
    private val pokemonList = mutableListOf<PokemonModel>()
    private val adapter = PokemonAdapter(pokemonList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRecycler()
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this, PokeViewModelFactory(PokeApiRepository(RetrofitClient.pokeApiService))
        )[PokemonListViewModel::class.java]
    }

    private fun setupRecycler() {
        viewModel.getPokemonList(0, 30).observe(viewLifecycleOwner) {
            pokemonList.addAll(it)
            binding.rvPokemonList.adapter = adapter
        }
    }
}