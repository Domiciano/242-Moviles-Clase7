package com.example.pokedex.data.util

import com.example.pokedex.data.service.FirebaseService
import com.example.pokedex.data.service.FirebaseServiceImpl
import com.example.pokedex.data.service.PokedexService
import com.example.pokedex.data.service.PokedexServiceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfiguration {

    private val pokedexRetrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokedexService: PokedexService = pokedexRetrofit.create(PokedexServiceImpl::class.java)


    private val firebaseRetrofit = Retrofit.Builder()
        .baseUrl("https://facelogprueba.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val firebaseService: FirebaseService = firebaseRetrofit.create(FirebaseServiceImpl::class.java)

}