package com.example.pokedex.data.service

import com.example.pokedex.data.dto.PokemonDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface FirebaseService {
    suspend fun createPokemon(codigo: String, pokemon: PokemonDTO)
}

interface FirebaseServiceImpl : FirebaseService {
    @POST("{codigo}/pokemon.json")
    override suspend fun createPokemon(@Path("codigo") codigo: String, @Body pokemon: PokemonDTO)
}
