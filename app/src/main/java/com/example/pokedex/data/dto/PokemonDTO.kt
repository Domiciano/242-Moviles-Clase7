package com.example.pokedex.data.dto

data class PokemonDTO(
    var name:String,
    var sprites: Sprite,
    var stats:List<StatContainer>
)

data class Sprite(
    var front_default:String
)

data class StatContainer(
    var base_stat:Int,
    var stat: Stat
)

data class Stat(
    var name:String
)