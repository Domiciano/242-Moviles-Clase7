package com.example.pokedex.data.service

import com.example.pokedex.data.dto.PokemonDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FirebaseService {
    suspend fun createPokemon(id: String, pokemon: PokemonDTO)
    suspend fun getAllPokemon(): List<PokemonDTO>
}

interface FirebaseServiceImpl : FirebaseService {
    @POST("{codigo}/pokemon.json")
    override suspend fun createPokemon(@Path("codigo") id: String, @Body pokemon: PokemonDTO)

    @GET("{codigo}/pokemon.json")
    override suspend fun getAllPokemon(): List<PokemonDTO>
}
