package com.example.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repo.FirebaseRepository
import com.example.pokedex.data.repo.FirebaseRepositoryImpl
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.data.repo.PokemonRepository
import com.example.pokedex.data.repo.PokemonRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexViewModel(
    val pokemonRepository: PokemonRepository = PokemonRepositoryImpl(),
    val firebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()
) : ViewModel() {

    private var _pokemonState = MutableLiveData<Pokemon>()
    val pokemonState: LiveData<Pokemon> get() = _pokemonState

    fun getPokemon(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //Llamar al repository
            var pokemon = pokemonRepository.getPokemon(name)
            Log.e(">>>", pokemon.name)
            withContext(Dispatchers.Main) { _pokemonState.value = pokemon }
        }
    }

    fun catchPokemon(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {


                _pokemonState.value?.let {
                    firebaseRepository.createPokemon(id, it)
                    Log.e(">>>", "Enviado...")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


}