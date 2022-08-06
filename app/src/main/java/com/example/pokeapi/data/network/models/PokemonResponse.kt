package com.example.pokeapi.data.network.models

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonModel>
)