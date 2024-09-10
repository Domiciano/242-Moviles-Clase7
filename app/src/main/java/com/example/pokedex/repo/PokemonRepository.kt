package com.example.pokedex.repo

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.service.PokedexService
import com.example.pokedex.service.RetrofitConfiguration

interface PokemonRepository {
    suspend fun getPokemon(name: String): Pokemon
}

class PokemonRepositoryImpl(
    val pokedexService: PokedexService = RetrofitConfiguration.pokedexService
) : PokemonRepository {

    override suspend fun getPokemon(name: String): Pokemon {
        var pokemonDTO = pokedexService.getPokemon(name)
        return Pokemon(
            pokemonDTO.sprites.front_default,
            pokemonDTO.name,
            "${pokemonDTO.stats[1].base_stat}",
            "${pokemonDTO.stats[2].base_stat}",
            "${pokemonDTO.stats[5].base_stat}",
            "${pokemonDTO.stats[0].base_stat}"
        )
    }

}