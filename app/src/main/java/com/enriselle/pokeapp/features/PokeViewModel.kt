package com.enriselle.pokeapp.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriselle.pokeapp.data.domain.repository.PokeRepository
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.data.source.network.resourse.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeRepository
): ViewModel() {
    private val _listPokemon = MutableStateFlow<List<PokemonInfo>>(emptyList())
    val listPokemon = _listPokemon.asStateFlow()
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    sealed class ValidationEvent {
        data object Success: ValidationEvent()
        data class Error(val message: String): ValidationEvent()
        data class Loading(val isLoading: Boolean): ValidationEvent()
    }

    init {
        fetchAllPokemon()
    }

    private fun fetchAllPokemon() {
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Loading(isLoading = true))
            when(val response = repository.getPokemonByGroup("0", "20")) {
                is Resource.Success -> {
                    response.data.results.forEach { pokemon ->
                        fetchOnePokemon(pokemon.name)
                    }

                    validationEventChannel.send(ValidationEvent.Loading(isLoading = false))
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun fetchOnePokemon(name: String) {
        viewModelScope.launch {
            when(val response = repository.getPokemonByName(name)) {
                is Resource.Success -> {
                    val updatedList = _listPokemon.value.toMutableList().apply {
                        this.add(response.data)
                    }

                    _listPokemon.value = updatedList
                }
                is Resource.Error -> {

                }
            }
        }
    }
}