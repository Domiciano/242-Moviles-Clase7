package com.example.pokedex.data.service

import com.example.pokedex.data.dto.PokemonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService{
    suspend fun getPokemon(pokemon: String): PokemonDTO
}

interface PokedexServiceImpl : PokedexService{
    @GET("pokemon/{pokemon}")
    override suspend fun getPokemon(@Path("pokemon") pokemon: String): PokemonDTO
}