package com.example.pokedex.data.repo

import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.data.dto.Sprite
import com.example.pokedex.data.dto.Stat
import com.example.pokedex.data.dto.StatContainer
import com.example.pokedex.data.service.FirebaseService
import com.example.pokedex.data.util.RetrofitConfiguration
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.data.service.PokedexService
import com.example.pokedex.data.service.PokedexServiceImpl

interface FirebaseRepository {
    suspend fun createPokemon(id: String, pokemon: Pokemon)
}

class FirebaseRepositoryImpl(
    val firebaseService: FirebaseService = RetrofitConfiguration.firebaseService
) : FirebaseRepository {

    override suspend fun createPokemon(id: String, pokemon: Pokemon) {
        val pokemonDTO = PokemonDTO(
            name = pokemon.name,
            sprites = Sprite(
                front_default = pokemon.image
            ),
            stats = listOf(
                StatContainer(base_stat = pokemon.health.toInt(), stat = Stat(name = "hp")),
                StatContainer(base_stat = pokemon.attack.toInt(), stat = Stat(name = "attack")),
                StatContainer(base_stat = pokemon.defense.toInt(), stat = Stat(name = "defense")),
                StatContainer(base_stat = 0, stat = Stat(name = "unknown")),
                StatContainer(base_stat = 0, stat = Stat(name = "unknown")),
                StatContainer(pokemon.speed.toInt(), stat = Stat(name = "speed")),
            ),
        )
        firebaseService.createPokemon(
            id, pokemonDTO
        )

    }

}