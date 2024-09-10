package com.example.pokedex.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

object RetrofitConfiguration {
    private val pokedexRetrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokedexService: PokedexService = pokedexRetrofit.create(PokedexService::class.java)
}

interface PokedexService {
    @GET("pokemon/{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemon: String): PokemonDTO
}

interface FirebaseService {
    @POST("{codigo}/pokemon.json")
    suspend fun catchPokemon(@Path("codigo") codigo: String, @Body pokemon:PokemonDTO)
}


data class PokemonDTO(
    var name:String,
    var sprites:Sprite,
    var stats:List<StatContainer>
)

data class Sprite(
    var front_default:String
)

data class StatContainer(
    var base_stat:Int,
    var stat:Stat
)

data class Stat(
    var name:String
)