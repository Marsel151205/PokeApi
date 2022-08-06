package com.example.pokeapi.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokeapi.data.network.RetrofitClient
import com.example.pokeapi.data.network.api.PokeApiService
import com.example.pokeapi.data.network.models.PokemonModel
import com.example.pokeapi.data.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeApiRepository(private val apiService: PokeApiService) {

    fun getPokemonList(offset: Int, limit: Int): MutableLiveData<List<PokemonModel>> {
        val data = MutableLiveData<List<PokemonModel>>()

        apiService.getPokemonList(offset, limit).enqueue(object :
            Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                response.body()?.let {
                    data.value = it.results
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                t.message?.let { Log.e("error", it) }
            }
        })
        return data
    }
}